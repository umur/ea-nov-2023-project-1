package com.ea.project.aop;

import com.ea.project.entity.ActivityLog;
import com.ea.project.respository.ActivityLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Around("@annotation(com.ea.project.aop.LogMe)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        String methodName = joinPoint.getSignature().toShortString();

        ActivityLog logEntry = new ActivityLog();
        logEntry.setDate(new Date());
        logEntry.setOperation(methodName);
        logEntry.setDuration(duration);

        activityLogRepository.save(logEntry);

        return result;
    }
}