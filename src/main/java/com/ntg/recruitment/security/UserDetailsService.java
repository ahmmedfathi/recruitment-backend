package com.ntg.recruitment.security;

import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new CustomUserDetails(
                user.getId(), // userId
                user.getEmail(),
                user.getPassword(),
                user.getRole() != null ?
                        java.util.List.of(() -> "ROLE_" + user.getRole().name())
                        : java.util.List.of()
        );
    }
}
