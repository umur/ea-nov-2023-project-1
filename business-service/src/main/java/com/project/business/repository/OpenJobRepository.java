package com.project.business.repository;

import com.project.business.model.OpenJob;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpenJobRepository extends ListCrudRepository<OpenJob, Long> {
    List<OpenJob> findAll();
    Optional<OpenJob> findById(Long id);

    List<OpenJob> findAllByCompany_Address_State(String state);

    List<OpenJob> findAllByCompany_Address_City(String city);

    List<OpenJob> findAllByCompany_Name(String companyName);

    @Override
    default void delete(OpenJob openJob) {
        openJob.setDeleted(true);
    }
}
