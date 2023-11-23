package com.project.business.repository;

import com.project.business.model.Address;
import com.project.business.model.Company;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends ListCrudRepository<Company, Long> {

    @Override
    default void delete(Company company) {
        company.setDeleted(true);
        Address address = company.getAddress();
        if (address != null) {
            address.setDeleted(true);
        }
        company.getProfExperiences()
                .forEach(profExperience -> profExperience.setDeleted(true));
        company.getOpenJobs()
                .forEach(openJob -> openJob.setDeleted(true));
    }
}
