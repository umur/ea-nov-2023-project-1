package com.alumni.JobService.service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.alumni.JobService.dto.PostingDto;
import com.alumni.JobService.entity.Job;
import com.alumni.JobService.entity.Posting;
import com.alumni.JobService.repository.JobRepository;
import com.alumni.JobService.repository.PostingRepository;
import com.alumni.JobService.service.PostingService;
import com.alumni.JobService.service.UserServiceClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService {
    private final PostingRepository postingRepo;
    private final JobRepository jobRepo;
    private final UserServiceClient userRepo;
    private final ModelMapper mapper;

    @Override
    public void save(PostingDto postingDto) {
        var job = jobRepo.save(mapper.map(postingDto.getJob(), Job.class));
        postingDto.getJob().setId(job.getId());
        postingRepo.save(mapper.map(postingDto, Posting.class));
    }

    @Override
    public List<PostingDto> findAll() {
        List<Posting> postings = postingRepo.findAll();
        var res = new ArrayList<PostingDto>();
        postings.forEach(posting -> {
            res.add(mapper.map(posting, PostingDto.class));
        });
        return res;
    }

    @Override
    public PostingDto findById(Long id) {
        var res = postingRepo.findById(id);
        return mapper.map(res, PostingDto.class);
    }

    @Override
    public void update(Long id, PostingDto updatedPosting) {
        var dbPosting = postingRepo.findById(id);
        if (dbPosting.isPresent()) {
            dbPosting.get().setCreationDate(updatedPosting.getCreationDate());
            dbPosting.get().setStatus(updatedPosting.getStatus());

            var user = userRepo.getUserById(updatedPosting.getPosterId());
            dbPosting.get().setPosterId(user.getId());

            // var job = jobRepo.findById(updatedPosting.getJobId()).orElse(null);
            var job = mapper.map(updatedPosting.getJob(), Job.class);

            jobRepo.save(job);

            dbPosting.get().setJob(job);
            postingRepo.save(dbPosting.get());
        }
    }

    @Override
    public void delete(Long id) {
        postingRepo.deleteById(id);
    }
}
