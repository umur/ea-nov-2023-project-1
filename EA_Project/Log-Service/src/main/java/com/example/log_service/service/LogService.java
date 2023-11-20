package com.example.log_service.service;

import com.example.log_service.model.Log;

import java.time.LocalDateTime;
import java.util.List;

public interface LogService {
    Log addLog(Log l);
    List<Log> getLogsByDateAndTime(LocalDateTime dt);
    List<Log> getLogsByUser(Long id);
    List<Log> getLogsByUserAndDateAndTime(Long id, LocalDateTime dt);
}
