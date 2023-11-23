package edu.ea.logservice.controller;

import edu.ea.logservice.model.Log;
import edu.ea.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    @PostMapping
    public ResponseEntity<String> addLog(@RequestBody Log log) {
        logService.addLog(log);
        return ResponseEntity.ok("Successfully added log");
    }
    @GetMapping("/user/{id}")
    public List<Log> getLogsByUserId(@PathVariable Long id){
        return logService.getLogsByUser(id);
    }
    @GetMapping("/{dateTime}")
    public List<Log> getLogsByDateAndTime(@PathVariable LocalDateTime dateTime){
        return logService.getLogsByDateAndTime(dateTime);
    }
}
