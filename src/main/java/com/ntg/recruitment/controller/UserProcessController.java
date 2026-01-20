package com.ntg.recruitment.controller;

import com.ntg.recruitment.dto.AddCandidatesToProcessDto;
import com.ntg.recruitment.dto.ProcessDto;
import com.ntg.recruitment.dto.UserProcessDto;
import com.ntg.recruitment.entity.Status;
import com.ntg.recruitment.repo.StatusRepo;
import com.ntg.recruitment.service.UserProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-process")
@RequiredArgsConstructor
public class UserProcessController {

    private final UserProcessService service;

    @PatchMapping
    public ResponseEntity <UserProcessDto> updateCandidate(
            @RequestBody @Valid UserProcessDto dto
    ) {
        return ResponseEntity.ok(service.updateCandidate(dto));
    }

    @PostMapping("/add-candidates")
    public ResponseEntity<ProcessDto> createProcessWithCandidates(
            @RequestBody @Valid AddCandidatesToProcessDto dto
    ) {
        ProcessDto createdProcess = service.createProcessWithCandidates(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProcess);
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity <UserProcessDto> updateCandidateStatus(
            @RequestBody @Valid UserProcessDto dto
    ) {
        return ResponseEntity.ok(service.updateCandidateStatus(dto));
    }


    @GetMapping("/process/{processId}")
    public ResponseEntity<List<UserProcessDto>> getByProcess(
            @PathVariable Long processId
    ) {
        return ResponseEntity.ok(service.getCandidatesByProcess(processId));
    }
    @GetMapping("/getAllStatus")
    public List<Status> getAllStatuses() {
        return service.getAllStatuses();

    }
}
