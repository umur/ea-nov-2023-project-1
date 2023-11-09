package com.project.alumni.repository;

import com.project.alumni.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {
    List<User> findAllByIdIn(List<Long> userIds);

    @Query("SELECT u FROM User u left join u.courses courses WHERE " +
    "u.industry LIKE CONCAT ('%', :query, '%')" +
    "Or u.gradYear LIKE CONCAT ('%', :query, '%')" +
    "Or u.address.city LIKE CONCAT ('%', :query, '%')" +
    "Or u.address.state LIKE CONCAT ('%', :query, '%')" +
    "Or courses.name LIKE CONCAT ('%', :query, '%')")
    List<User> searchUsersDirectory(String query);

    List<User> findByAddressId(Long addressId);


    @Query("select u from User u left join u.courses courses where " +
    "u.gradYear like concat('%', ?1, '%')" +
    "or courses.id in ?2" +
            "or u.address.city like concat('%', ?3, '%')" +
    "or u.address.state like concat('%', ?4, '%')" +
            "or u.industry like concat('%', ?5, '%')")
    List<User> findUsersInDirectory(
                                    String gradYear,
                                    Collection<Long> courses_id,
                                    String address_city,
                                    String address_state,
                                    String industry);
}
