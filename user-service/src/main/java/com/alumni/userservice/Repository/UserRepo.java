package com.alumni.userservice.Repository;

import com.alumni.userservice.Entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<User, Long> {
    @Override
    List<User> findAll();
    @Override
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String name);

}
