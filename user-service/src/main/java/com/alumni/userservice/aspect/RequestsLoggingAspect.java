package com.alumni.userservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestsLoggingAspect {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    // Setup pointcuts
    @Pointcut("execution(* com.alumni.userservice.controller.*.*(..))")
    private void forControllerPackage(){

    }

    @Pointcut("execution(* com.alumni.userservice.service.*.*(..))")
    private void forServicePackage(){

    }

    @Pointcut("execution(* com.alumni.userservice.repository.*.*(..))")
    private void forRepositoryPackage(){

    }

    // Combining point cuts
    @Pointcut("forControllerPackage() || forServicePackage() || forRepositoryPackage()")
    private void forAppFlow() {

    }

    // Add at Before Advice for all the point cuts
    @Before("forAppFlow()")
    public void before(JoinPoint theJointPoint){

        // Display the method we are calling
        String theMethod = theJointPoint.getSignature().toShortString();
        LOGGER.info("====>> in @Before: calling method: " +theMethod);
        // Display the arguments to the method

        // Get the arguments
        Object[] args = theJointPoint.getArgs();

        // Loop through and display args
        for(Object tempArgs: args){
            LOGGER.info("=====>> in argument: " +tempArgs);
        }

//        String username = getLoggedInUserUsername();
//        auditLogService.save(username, AuditAction.FETCH_USERS);
//        auditLogService.save(username, AuditAction.LOGIN);
//        auditLogService.save(username, AuditAction.DELETE_USER);
//        auditLogService.save(username, AuditAction.CREATE_USER);
//        auditLogService.save(username, AuditAction.UPDATE_USER_INFO);

    }

//    private String getLoggedInUserUsername(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if(auth.getPrincipal() instanceof CustomUserDetails userDetails) {
//            return userDetails.getUsername();
//        }
//        return auth.getPrincipal().toString();
//    }

}
