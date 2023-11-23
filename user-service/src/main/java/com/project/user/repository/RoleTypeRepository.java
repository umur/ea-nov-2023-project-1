package com.project.user.repository;

import com.project.user.model.type.RoleType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleTypeRepository extends ListCrudRepository<RoleType, Long> {
}
