package com.project.alumni.service.Job.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.alumni.dto.Job.PostingDto;
import com.project.alumni.entity.Job.Job;
import com.project.alumni.entity.Job.Posting;
import com.project.alumni.repository.UserRepository;
import com.project.alumni.repository.Job.JobRepository;
import com.project.alumni.repository.Job.PostingRepository;
import com.project.alumni.service.Job.PostingService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService {
    private final PostingRepository postingRepo;
    private final JobRepository jobRepo;
    private final UserRepository userRepo;
    private final ModelMapper mapper;

    @Override
    public void save(PostingDto postingDto) {
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

            var user = userRepo.findById(updatedPosting.getPosterId()).orElse(null);
            dbPosting.get().setPoster(user);

            // var job = jobRepo.findById(updatedPosting.getJobId()).orElse(null);
            var job = mapper.map(updatedPosting.getJob(), Job.class)
            dbPosting.get().setJob(job);

            postingRepo.save(dbPosting.get());
        }
    }

    @Override
    public void delete(Long id) {
        postingRepo.deleteById(id);
    }
}
