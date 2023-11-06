package com.project.alumni.repository;

import com.project.alumni.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {
    List<User> findAllByIdIn(List<Long> userIds);

    List<User> findByAddressId(Long addressId);

    List<User> findAllByGradYearContainsOrCoursesIdInOrAddressCityContainsOrAddressStateContainsOrIndustryContains(String gradYear, Collection<Long> courses_id, String address_city, String address_state, String industry);
}
