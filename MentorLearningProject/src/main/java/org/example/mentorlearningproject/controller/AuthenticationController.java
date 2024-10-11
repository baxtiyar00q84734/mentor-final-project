package org.example.mentorlearningproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.securityDTOs.LoginRequest;
import org.example.mentorlearningproject.dto.securityDTOs.LoginResponse;
import org.example.mentorlearningproject.dto.securityDTOs.RegisterRequest;
import org.example.mentorlearningproject.exception.MasterException;
import org.example.mentorlearningproject.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(
            @RequestBody RegisterRequest request
    ) throws MasterException {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginRequest request
    ) throws MasterException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
