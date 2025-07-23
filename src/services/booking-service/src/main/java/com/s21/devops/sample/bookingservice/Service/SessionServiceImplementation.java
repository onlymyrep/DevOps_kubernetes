package com.s21.devops.sample.bookingservice.Service;

import com.s21.devops.sample.bookingservice.Security.JwtProvider;
import com.s21.devops.sample.bookingservice.Security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionServiceImplementation {
    private final JwtProvider jwtProvider;

    public SessionServiceImplementation(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public UUID getCurrentUserUid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUserUid();
    }

    // Другие методы
}