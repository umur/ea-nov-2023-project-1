package com.project.user.aop;

import com.project.user.model.Alumni;
import com.project.user.repository.AlumniRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {
    
    private final HttpServletRequest httpServletRequest;
    private final AlumniRepository alumniRepository;
    
//    @Before(@Before("@annotation(com.project.user.annotation.AdminAuthorization) && args(id, ..)"))
    @Before("@annotation(com.project.user.aop.annotation.CombinedAuthorization) && args(id)")
    public void checkCombinedAuthorization(Long id) throws AccessDeniedException {
        var mail = httpServletRequest.getHeaders("mail");
        var user = alumniRepository.findAlumniByMail(String.valueOf(mail));
        if (!isAdmin(user) || !isCurrentUser(user,id)) {
            throw new AccessDeniedException("Access denied. Only administrators and the current user are allowed.");
        }
    }

    @Before("@annotation(com.project.user.aop.annotation.AdminAuthorization)")
    public void checkAdminAuthorization(JoinPoint joinPoint) throws AccessDeniedException {
        var mail = httpServletRequest.getHeaders("mail");
        var user = alumniRepository.findAlumniByMail(String.valueOf(mail));
        if (!isAdmin(user)) {
            throw new AccessDeniedException("Access denied. Only administrators are allowed.");
        }
    }

    private boolean isAdmin(Optional<Alumni> user) {
        return user.map(u -> u.getRole().getName().equals("ADMIN")).orElse(false);
    }

    private boolean isCurrentUser(Optional<Alumni> user, Long id) {
        return user.map(u -> u.getId().equals(id)).orElse(false);
    }
}