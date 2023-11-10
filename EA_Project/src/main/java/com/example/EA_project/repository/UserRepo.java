package com.example.EA_project.repository;


import com.example.EA_project.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<User, Long> {


    Optional<User> findByEmail(String email);

    List<User> findByMajorAndIsDeleted(String major, boolean d);
    List<User> findByNameContainingAndIsDeleted(String name, boolean d);
    List<User> findByIdContainingAndIsDeleted(Long id, boolean d);

    List<User> findAllByIsDeleted(boolean d);

    @Modifying
    @Query(value = "UPDATE `ea-project`.`user` SET `is_deleted` = true WHERE (`id` = ?);\n",
            nativeQuery = true)
    void updateUserByIdIs(Long id);
}