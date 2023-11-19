package com.project.alumni.repository.user;

import com.project.alumni.entity.user.Role;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
