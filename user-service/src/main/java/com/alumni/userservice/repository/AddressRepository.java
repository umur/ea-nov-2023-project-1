package com.alumni.userservice.repository;

import com.alumni.userservice.entity.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepository extends ListCrudRepository<Address, Long> {
}
