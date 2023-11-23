package com.project.auth.services.feign;

import com.project.auth.entities.AuthRequest;
import com.project.auth.entities.value_objects.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Component
@FeignClient("user-service")
public interface UserServiceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/alumni")
    UserVO saveUser(@RequestBody AuthRequest authRequest);
    @RequestMapping(method = RequestMethod.GET, value = "/alumni/searchByMail/{mail}")
    UserVO getUserByMail(@PathVariable String mail);

}