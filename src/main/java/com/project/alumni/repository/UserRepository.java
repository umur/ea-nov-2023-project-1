package com.project.alumni.repository;

import com.project.alumni.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {
    List<User> findAllByIdIn(List<Long> userIds);

    @Query("SELECT u FROM User u WHERE " +
            "u.industry LIKE CONCAT('%',:query, '%')" +
            "Or u.gradYear LIKE CONCAT('%', :query, '%')" +
            "Or u.address.city LIKE CONCAT('%', :query, '%')" +
            "Or u.address.state LIKE CONCAT('%', :query, '%')")
    List<User> searchUsers(String query);

    /**
     * Return all users by a given selected Address.
     */
    List<User> findByAddressId(Long addressId);

}
