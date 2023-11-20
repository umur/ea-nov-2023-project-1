package edu.ea.jobservice.service.impl;

import com.example.EA_project.entity.Job;
import com.example.EA_project.service.JobService;
import edu.ea.jobservice.model.Student;
import edu.ea.jobservice.repository.JobRepo;
import edu.ea.jobservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepo jobRepo;
    private final StudentService studentService;

    @Override
    public List<Job> findAll() {
        return  jobRepo.findAll();
    }

    @Override
    public void add(Job job) {
        jobRepo.save(job);
    }

    @Override
    public void remove(int id) {
        Optional<Job> job=jobRepo.findById(id);
        if(job.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            jobRepo.deleteById(id);
        }
    }


    @Override
    public void update(Job job) {
        Optional<Job> job0=jobRepo.findById(job.getId());
        if(job0.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            jobRepo.save(job);
        }

    }

    @Override
    public void apply(int jobId, int studentId) {
        Optional<Job> jobO = jobRepo.findById(jobId);
        Student student = studentService.findById(studentId);
        if(jobO.isEmpty()){
            throw new IllegalArgumentException();
        }
        else{
            Job job = jobO.get();
            if(job.getAppliedStudents() == null){
                job.setAppliedStudents(new ArrayList<>());
            }
            List<Student> students = job.getAppliedStudents();
            students.add(student);
            job.setAppliedStudents(students);
            update(job);
        }


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
