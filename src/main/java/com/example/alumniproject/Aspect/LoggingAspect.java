package com.example.alumniproject.Aspect;

import java.time.LocalDateTime;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.alumniproject.entity.User;
import com.example.alumniproject.models.Log;
import com.example.alumniproject.repository.LogRepo;
import com.example.alumniproject.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final LogRepo logRepo;
    private final UserRepo userRepo;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void methods() {

    }

    @Before("methods()")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LocalDateTime date = LocalDateTime.now();
        String method = joinPoint.getSignature().getName();
        String url = request.getRequestURL().toString();

        if (authentication != null && authentication.isAuthenticated()) {
            Optional<User> user = userRepo.findByEmail(authentication.getName());

            if (user.isPresent()) {
                Log log = new Log();
                log.setName(user.get().getFirstName());
                log.setRole(user.get().getRole());
                log.setDate(date);
                log.setMethod(method);
                log.setUrl(url);

                logRepo.save(log);
            }
        }
    }
}
