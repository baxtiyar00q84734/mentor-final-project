package org.example.mentorlearningproject.service;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.securityDTOs.LoginRequest;
import org.example.mentorlearningproject.dto.securityDTOs.LoginResponse;
import org.example.mentorlearningproject.dto.securityDTOs.RegisterRequest;
import org.example.mentorlearningproject.entity.Master;
import org.example.mentorlearningproject.enums.Role;
import org.example.mentorlearningproject.exception.MasterException;
import org.example.mentorlearningproject.repository.MasterRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final MasterRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public LoginResponse register(RegisterRequest registerForum) throws MasterException {
        var user= Master.builder()
                .firstName(registerForum.getFirstName())
                .lastName(registerForum.getLastName())
                .email(registerForum.getEmail())
                .password(passwordEncoder.encode(registerForum.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

    public LoginResponse authenticate(LoginRequest request) throws MasterException{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
