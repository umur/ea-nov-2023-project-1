package ea.project.student.repository;


import ea.project.student.entity.Student;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends ListCrudRepository<Student, Integer> {
    List<Student> findByGraduationYear(@Param("graduationYear") String graduationYear);
    List<Student> findByCoursesContaining(String course);
    List<Student> findByAddressId(int addressId);
    List<Student> findByIndustry(@Param("industry") String industry);

}
