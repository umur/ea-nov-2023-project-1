package ea.project.student.service;

import ea.project.student.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
     void add(Student student);
     void remove(int id);
     List<Student> findAll();
     void update(Student address);
     List<Student> findByGradution(String year);
     List<Student> findByCourse(String course);
     List<Student> findByLocation(int addressId);
     List<Student> findByIndustry(String industry);


     Student findById(int studentId);
}
