package com.project.alumni.repository;

import com.project.alumni.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {
<<<<<<< HEAD

=======
    List<User> findAllByIdIn(List<Long> userIds);
>>>>>>> 3ba3dc940d32483e0b6d649b8a0cfd39fa3192bf
}
