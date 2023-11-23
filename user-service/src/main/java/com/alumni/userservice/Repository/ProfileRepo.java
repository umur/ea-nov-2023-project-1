package com.alumni.userservice.Repository;

import com.alumni.userservice.Entity.Profile;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends ListCrudRepository<Profile, Long> {

}
