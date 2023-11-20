package edu.ea.logservice.repository;

import edu.ea.logservice.model.Log;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepo extends ListCrudRepository<Log,Long> {

     List<Log> getLogsByTimeIs(LocalDateTime dt);

     List<Log> getLogsByUser_IdIs(Long id);

     List<Log> getLogsByUser_IdIsAndTimeIs(Long id, LocalDateTime dt);


}
