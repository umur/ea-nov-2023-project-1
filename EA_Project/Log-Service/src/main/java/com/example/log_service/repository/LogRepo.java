package com.example.log_service.repository;

import com.example.log_service.model.Log;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepo extends ListCrudRepository<Log,Long> {

    public List<Log> getLogsByTimeIs(LocalDateTime dt);

    public List<Log> getLogsByUser_IdIs(Long id);

    public List<Log> getLogsByUser_IdIsAndTimeIs(Long id, LocalDateTime dt);


}
