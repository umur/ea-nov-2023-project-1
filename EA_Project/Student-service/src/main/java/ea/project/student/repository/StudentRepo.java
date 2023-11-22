package ea.project.student.repository;


import ea.project.student.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends ListCrudRepository<Student, Integer> {
    List<Student> findByGraduationYearAndDeletedFalse(@Param("graduationYear") String graduationYear);
    List<Student> findByCoursesContainingAndDeletedFalse(String course);
    @Query("SELECT   s FROM  Student  s where  s.address.city=?1 and s.deleted=false ")
    List<Student> findByAddressByCity(String city);
    @Query("SELECT  s FROM  Student  s where  s.address.state=?1 and s.deleted=false ")
    List<Student> findByAddressState(String city);

    @Query("SELECT s FROM  Student  s where  s.address.zip=?1 and s.deleted=false ")
    List<Student> findByAddressZip(String zip);

    List<Student> findByIndustryAndDeletedFalse(@Param("industry") String industry);

    Optional<Student> findByUserId(Long userId);

}
