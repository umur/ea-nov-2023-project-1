package com.project.alumni.chatservice.service.external;

import com.project.alumni.chatservice.entity.ExternalUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("user-service")
public interface UsersClient {
    // get list users by ids
    @GetMapping("/api/users/byIds")
    List<ExternalUserDto> findAllByIdIn(@RequestParam List<Long> ids);
}