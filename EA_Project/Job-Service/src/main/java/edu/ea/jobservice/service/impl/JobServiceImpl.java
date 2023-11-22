package  edu.ea.jobservice.service.impl;



import edu.ea.jobservice.model.Job;
import edu.ea.jobservice.repository.JobRepo;
import edu.ea.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepo jobRepo;

    private final  JwtService jwtService;

    @Override
    public List<Job> findAll() {
        return  jobRepo.findAll();
    }

    @Override
    public void add(Job job) {
        Long userIdFromToken = jwtService.getUserIdFromToken();
        job.setUserId(userIdFromToken);
        jobRepo.save(job);
    }

    @Override
    public void remove(int id) throws Exception {
        Optional<Job> job=jobRepo.findById(id);
        if(job.isEmpty())
           throw new Exception("Job not found");
        Job job1 = job.get();
        job1.setDeleted(true);
        jobRepo.save(job1);

    }


    @Override
    public void update(Job job) throws Exception {
        Optional<Job> job0=jobRepo.findById(job.getId());
        if(job0.isEmpty())
            throw new Exception("Job not found");
            jobRepo.save(job);


    }

    @Override
    public void apply(int jobId) throws Exception {
        Optional<Job> job0=jobRepo.findById(jobId);
        if(job0.isEmpty())
            throw new Exception("Job not found");
        Job job = job0.get();
        job.getAppliedUsers().add(jwtService.getUserIdFromToken());
        jobRepo.save(job);
    }


    @Override
    public List<Job> getByCity(String city) {
        return null;
    }

    @Override
    public List<Job> getByState(String state) {
        return null;
    }

    @Override
    public List<Job> getByCompanyName(String companyName) {
        return jobRepo.findByCompanyName(companyName);
    }

	@Override
	public Job findById(int id) {
		Optional<Job> job = jobRepo.findById(id);
		if(job.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return job.get();
	}


}
