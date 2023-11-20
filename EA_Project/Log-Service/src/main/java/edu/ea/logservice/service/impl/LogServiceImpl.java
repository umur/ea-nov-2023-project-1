package edu.ea.logservice.service.impl;

import edu.ea.logservice.model.Log;
import edu.ea.logservice.repository.LogRepo;
import edu.ea.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LogServiceImpl implements LogService {

    private final LogRepo logRepo;

    @Override
    public Log addLog(Log l){
        return logRepo.save(l);
    }
    @Override
    public List<Log> getLogsByDateAndTime(LocalDateTime dt){
        return logRepo.getLogsByTimeIs(dt);
    }
    @Override
    public List<Log> getLogsByUser(Long id){
        return logRepo.getLogsByUser_IdIs(id);
    }
    @Override
    public List<Log> getLogsByUserAndDateAndTime(Long id, LocalDateTime dt){
        return logRepo.getLogsByUser_IdIsAndTimeIs(id, dt);
    }
}
