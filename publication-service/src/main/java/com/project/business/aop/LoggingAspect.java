package com.project.business.aop;

import com.project.business.model.Log;
import com.project.business.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@RequiredArgsConstructor
@Component
public class LoggingAspect {
    private final LogRepository logRepository;

    @Around("within(com.project.business.controller..*)")
  public Object logger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
        String operation=proceedingJoinPoint.getSignature().getName();
        Object result=proceedingJoinPoint.proceed();
        long endTime=System.currentTimeMillis();
        long takenTime=endTime-startTime;
        logRepository.save(
                new Log(LocalDateTime.now(),operation,takenTime));
        return result;
    }

}
