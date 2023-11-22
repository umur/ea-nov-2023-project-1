package ea.project.student.service;

import ea.project.student.entity.Student;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
     void add(Student student) throws Exception;
     void remove(long userId) throws Exception;
     List<Student> findAll();
     void update(Student address) throws Exception;
     List<Student> findByGradution(String year);
     List<Student> findByCourse(String course);
     List<Student> findByState(String state);
     List<Student> findByCity(String city);
     List<Student> findByZip(String zip);
     List<Student> findByIndustry(String industry);


     Student findById(int studentId) throws Exception;
}
