package com.example.EA_project.Aspect;


import com.example.EA_project.entity.Log;
import com.example.EA_project.repository.LogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
@Service
public class LogService {

    private final LogRepo loRepo;


    public Log addLog(Log l){
        return loRepo.save(l);
    }

    public List<Log> getLogsByDateAndTime(LocalDateTime dt){
        return loRepo.getLogsByTimeIs(dt);
    }

    public List<Log> getLogsByUser(Long id){
        return loRepo.getLogsByUser_IdIs(id);
    }

    public List<Log> getLogsByUserAndDateAndTime(Long id, LocalDateTime dt){
        return loRepo.getLogsByUser_IdIsAndTimeIs(id, dt);
    }
}
