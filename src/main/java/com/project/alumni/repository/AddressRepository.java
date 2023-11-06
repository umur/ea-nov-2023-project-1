package com.project.alumni.repository;

import com.project.alumni.entity.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepository extends ListCrudRepository<Address, Long> {
}
