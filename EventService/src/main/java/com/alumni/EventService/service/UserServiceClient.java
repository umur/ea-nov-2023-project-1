package com.alumni.EventService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alumni.EventService.dto.UserFullDetailsDto;

@Component
@FeignClient("userservice")
public interface UserServiceClient {
    @GetMapping("/api/users/profile/{id}")
    UserFullDetailsDto getUserById(@RequestParam Long id);
}
