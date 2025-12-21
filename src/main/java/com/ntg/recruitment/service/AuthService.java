package com.ntg.recruitment.service;


import com.ntg.recruitment.dto.AuthResponse;
import com.ntg.recruitment.dto.LoginRequest;
import com.ntg.recruitment.dto.RegisterRequest;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.repo.UserRepository;
import com.ntg.recruitment.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository repo,
                       PasswordEncoder encoder,
                       JwtService jwtService) {
        this.userRepo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email exists");

        User user = new User();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(req.getRole());

        userRepo.save(user);
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow();

        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getRole().name());
    }
}

