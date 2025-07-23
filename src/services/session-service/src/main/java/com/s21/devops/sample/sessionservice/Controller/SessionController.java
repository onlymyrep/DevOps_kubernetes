package com.s21.devops.sample.sessionservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
    
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserReq request) {
        // Логика регистрации
        return ResponseEntity.ok("User created");
    }
}