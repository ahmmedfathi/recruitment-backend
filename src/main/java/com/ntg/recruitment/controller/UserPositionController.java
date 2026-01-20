package com.ntg.recruitment.controller;

import com.ntg.recruitment.dto.UserDtoResponse;
import com.ntg.recruitment.dto.UserPositionDto;
import com.ntg.recruitment.entity.User;
import com.ntg.recruitment.security.CustomUserDetails;
import com.ntg.recruitment.service.UserPositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserPositionController {

    @Autowired
    private UserPositionService userPositionService;

    @PostMapping("/apply/{positionId}")
    public ResponseEntity<String> applyToJob(
            @PathVariable Long positionId,
            @Valid @RequestBody UserPositionDto dto,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        dto.setUserId(currentUser.getId());
        dto.setPositionId(positionId);

        userPositionService.applyToJob(dto);

        return ResponseEntity.ok("Applied successfully");
    }

    @GetMapping("/getAllCandidates/{positionId}")
    public ResponseEntity<List<User>> getAllCandidatesByPosition(
            @PathVariable Long positionId
    ) {
        List<User> candidates = userPositionService.getAllCandidatesByPosition(positionId);
        return ResponseEntity.ok(candidates);
    }
}
