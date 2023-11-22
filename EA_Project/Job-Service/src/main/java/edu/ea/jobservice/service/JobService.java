package  edu.ea.jobservice.service;


import edu.ea.jobservice.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    public void add(Job job);
    public void remove(int id) throws Exception;
    public void update(Job job) throws Exception;

    public void apply(int jobId) throws Exception;

    public List<Job> getByCity(String city);

    public List<Job> getByState(String state);

    public List<Job> getByCompanyName(String companyName);

    List<Job> findAll();
	public Job findById(int id);

}
