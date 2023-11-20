package com.example.log_service.service.impl;

import com.example.log_service.model.Log;
import com.example.log_service.repository.LogRepo;
import com.example.log_service.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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
