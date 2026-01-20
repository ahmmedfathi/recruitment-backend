package com.ntg.recruitment.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntg.recruitment.dto.UserDto;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.repo.UserRepository;
import com.ntg.recruitment.security.CustomUserDetails;
import com.ntg.recruitment.service.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;
    private final UserService userService;

    public UserController(UserService userService, UserRepository repo) {
        this.repo=repo;
        this.userService = userService;
    }



    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return Map.of("message", "User deleted successfully");
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


//    @GetMapping("/status/{statusId}")
//    public List<User> getUsersByStatus(@PathVariable Long statusId) {
//        return userService.getUsersByStatus(statusId);
//    }



    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String username) {
        return userService.searchUsersByUsername(username);
    }
    @GetMapping("/candidates")
    public List<User> getAllCandidates() {
        return userService.getAllCandidates();
    }

    @PatchMapping("/profile")
    public UserDto updateUser(
            @RequestBody UserDto dto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        return userService.updateUserProfile(userId, dto);
    }






}
