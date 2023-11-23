package com.project.user.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@RequiredArgsConstructor
@Component
public class LoggingAspect {

    @Around("within(com.project.user.controller..*)")
  public Object logger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
        String operation=proceedingJoinPoint.getSignature().getName();
        Object result=proceedingJoinPoint.proceed();
        long endTime=System.currentTimeMillis();
        long takenTime=endTime-startTime;
//        logRepository.save(
//                new Log(LocalDateTime.now(),operation,takenTime));
        return result;
    }

}
