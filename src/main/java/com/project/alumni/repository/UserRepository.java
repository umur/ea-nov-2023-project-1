package com.project.alumni.repository;

import com.project.alumni.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {
    List<User> findAllByIdIn(List<Long> userIds);

    @Query("SELECT u FROM User u WHERE " +
    "u.industry LIKE CONCAT ('%', :query, '%')" +
    "Or u.gradYear LIKE CONCAT ('%', :query, '%')" +
    "Or u.address.city LIKE CONCAT ('%', :query, '%')" +
    "Or u.address.state LIKE CONCAT ('%', :query, '%')" )
    //"Or u.courses LIKE CONCAT ('%', :query, '%')")
    List<User> searchUsersDirectory(String query);

    List<User> findByAddressId(Long addressId);


    List<User> findAllByGradYearContainsOrCoursesIdInOrAddressCityContainsOrAddressStateContainsOrIndustryContains(String gradYear, Collection<Long> courses_id, String address_city, String address_state, String industry);
}
