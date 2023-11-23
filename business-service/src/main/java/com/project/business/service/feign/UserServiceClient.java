package com.project.business.service.feign;

import com.project.business.dto.get.GetAlumniDto;
import com.project.business.dto.get.GetFullAlumniDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Component
@FeignClient("USER-SERVICE")
public interface UserServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/alumni/searchByMail/{mail}")
    GetAlumniDto getUser(@PathVariable String mail);
    @RequestMapping(method = RequestMethod.GET, value = "/alumni/{id}")
    GetAlumniDto getUser(@PathVariable Long id);

}
