package com.ntg.recruitment.service;


import com.ntg.recruitment.dto.AuthResponse;
import com.ntg.recruitment.dto.ChangePasswordRequest;
import com.ntg.recruitment.dto.LoginRequest;
import com.ntg.recruitment.dto.RegisterRequest;
import com.ntg.recruitment.entity.Role;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.repo.UserRepository;
import com.ntg.recruitment.security.JwtService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepo,
                       PasswordEncoder encoder,
                       JwtService jwtService) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest req) {

        if (userRepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email exists");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setRole(req.getRole());

        user.setPassword(encoder.encode(req.getPassword()));

        userRepo.save(user);
    }


    public AuthResponse login(LoginRequest req) {

        User user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                user.getId(),           // id
                token,                  // token
                user.getEmail(),        // email
                user.getUsername(),     // username
                user.getFullName(),     // fullName
                user.getPhoneNumber(),   // phoneNumber
                null,                   // birthDate (لأن الـ User قد لا يحتوي عليه حالياً)
                user.getUniversity(),   // University
                user.getFaculty(),      // faculty
                user.getDepartment(),   // department
                user.getGraduationYear(), // graduationYear (تعامل مع الـ null)
                user.getRole().name()   // role
        );
    }
    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("المستخدم غير موجود"));

        // تحقق من كلمة المرور القديمة
        if (!encoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("كلمة المرور القديمة غير صحيحة");
        }

        // تشفير كلمة المرور الجديدة
        user.setPassword(encoder.encode(request.getNewPassword()));

        // الحفظ ليس ضروريًا هنا لأن @Transactional يتكفل به
        userRepo.save(user);
    }




}

