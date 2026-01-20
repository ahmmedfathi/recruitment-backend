package com.ntg.recruitment.controller;

import com.ntg.recruitment.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

import com.ntg.recruitment.dto.AuthResponse;
import com.ntg.recruitment.dto.ChangePasswordRequest;
import com.ntg.recruitment.dto.LoginRequest;
import com.ntg.recruitment.dto.RegisterRequest;
import com.ntg.recruitment.service.AuthService;
import com.sun.security.auth.UserPrincipal;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private  AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }



    @PatchMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(
            @RequestBody ChangePasswordRequest request,
            @AuthenticationPrincipal CustomUserDetails principal
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            authService.changePassword(principal.getId(), request);
            response.put("success", true);
            response.put("message", "تم تغيير كلمة المرور بنجاح");
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            response.put("success", false);
            response.put("message", ex.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }


}
