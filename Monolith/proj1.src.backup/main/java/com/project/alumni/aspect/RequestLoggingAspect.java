package com.project.alumni.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
@RequiredArgsConstructor
@Component
@Aspect
public class RequestLoggingAspect {

    final private HttpServletRequest request;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Before("execution(* com.project.alumni.controller.*.*(..))")
    public void logRequestInPackage(JoinPoint joinPoint) {
        logRequest(joinPoint);
    }

    @Before("execution(* com.project.alumni.controller.*.*.*(..))")
    public void logRequestInFeaturesPackage(JoinPoint joinPoint) {
        logRequest(joinPoint);
    }

    private void logRequest(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("Request received to {}.{}()", className, methodName);
        logger.info("Request URL: {}", request.getRequestURL());
        logger.info("Request Type: {}", request.getMethod());
        logger.info("Request IP: {}", request.getRemoteAddr());

        request.getParameterMap().forEach((key, value) -> {
            logger.info("{}: {}", key, value);
        });
    }
}
