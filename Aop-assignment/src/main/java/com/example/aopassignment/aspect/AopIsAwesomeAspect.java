package com.example.aopassignment.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component

public class AopIsAwesomeAspect {
    @Pointcut("execution(* com.example.aopassignment.service..*(..))")
    public void serviceAction() {
    }
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postAction() {
    }
    @Before(" serviceAction() && postAction()")
    public void checkHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String headerValue = request.getHeader("AOP-IS-AWESOME");
        if (headerValue == null || headerValue.isEmpty()) {
            throw new AopIsAwesomeException("AOP-IS-AWESOME header is missing in the POST request.");
        }
    }
}
