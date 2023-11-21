package com.alumni.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Component
@FeignClient("user-service")
public interface UsersClient {
    // get user by id
    @GetMapping("/users")
    Optional<UsersClient> findById(@RequestParam Long id);
}