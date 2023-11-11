package edu.miu.ea.ap.repository;

import edu.miu.ea.ap.helper.RefreshableCRUDRepository;
import edu.miu.ea.ap.model.domain.APUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APUserRepository extends RefreshableCRUDRepository<APUser, Long> {

    APUser findByUsernameIgnoreCaseAndRetiredFalse(String username);
    List<APUser> findAllByOrderById();

}
