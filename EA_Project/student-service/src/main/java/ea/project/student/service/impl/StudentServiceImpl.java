package ea.project.student.service.impl;


import ea.project.student.entity.Student;
import ea.project.student.repository.StudentRepo;
import ea.project.student.service.StudentService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Value("${student.queue}")
    private  String deleteUserQueue;
    private final StudentRepo studentRepo;
  private  final  JwtService jwtService;
    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo,JwtService jwtService) {

        this.studentRepo = studentRepo;
        this.jwtService=jwtService;
    }

    @Override
    public void add(Student student) throws Exception {
        System.out.println(" student id from  Token" + jwtService.getUserIdFromToken());
        Long userIdFromToken = jwtService.getUserIdFromToken();
        if(userIdFromToken==null)
            throw  new Exception("User Id not found in token");

        Optional<Student> byUserId = studentRepo.findByUserId(userIdFromToken);
        if(byUserId.isPresent())
            throw  new  Exception("User already has profile");
        student.setUserId(userIdFromToken);

        studentRepo.save(student);
    }

    @Override
   // @RabbitListener(queues = {"deleted.user.queue"})
    public void remove(long userId) throws Exception {

        Optional<Student> student = studentRepo.findByUserId(userId);
        if(student.isEmpty()) {
            System.out.println("Student profile not exists for user id:" + userId );
            return;
        }

        Student student1 = student.get();
        student1.setDeleted(true);
        studentRepo.save(student1);
        System.out.println("Student profile removed belong user id " + userId);

    }

    @Override
    public List<Student> findAll() {

        return studentRepo.findAll();
    }

    @Override
    public void update(Student student) throws Exception {

        Optional<Student> studentO = studentRepo.findById(student.getId());
        Long userIdFromToken = jwtService.getUserIdFromToken();
        if(studentO.isEmpty()){
          throw  new Exception("Student profile not exits ");
        }
        else{
            System.out.println("Token User id:" +userIdFromToken);
            System.out.println("Student  User id:" + studentO.get().getUserId());
            if(studentO.get().getUserId()!=userIdFromToken)
                throw  new Exception("only profile owner can update Student profile");

            studentRepo.save(student);
        }
    }

    @Override
    public List<Student> findByGradution(String year) {
        return studentRepo.findByGraduationYearAndDeletedFalse(year);
    }
    @Override
    public List<Student> findByCourse(String course) {
        return studentRepo.findByCoursesContainingAndDeletedFalse(course);
    }

    @Override
    public List<Student> findByState(String state) {
           return studentRepo.findByAddressState(state);
    }

    @Override
    public List<Student> findByCity(String city) {
        return studentRepo.findByAddressByCity(city);
    }

    @Override
    public List<Student> findByZip(String zip) {
        return studentRepo.findByAddressZip(zip);
    }


    @Override
    public List<Student> findByIndustry(String industry) {
        return studentRepo.findByIndustryAndDeletedFalse(industry);
    }

    @Override
    public Student findById(int studentId) throws Exception {
        Optional<Student> student = studentRepo.findById(studentId);
        if(student.isEmpty()){
            throw new Exception("Student not found");
        }
        return student.get();
    }
}
