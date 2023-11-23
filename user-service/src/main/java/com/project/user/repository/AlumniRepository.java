package com.project.user.repository;

import com.project.user.model.Alumni;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumniRepository extends ListCrudRepository<Alumni,Long> {
    default void deleteById(Long alumniId){
        delete(findById(alumniId).orElseThrow());
    }
    @Override
    default void delete(Alumni alumni) {
        alumni.setDeleted(true);
    }
    Optional<Alumni> findAlumniByMail(String mail);
    Optional<Alumni> findAlumniByLocation(String location);

}
















