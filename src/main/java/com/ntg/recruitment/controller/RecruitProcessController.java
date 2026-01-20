package com.ntg.recruitment.controller;

import com.ntg.recruitment.dto.ProcessDto;
import com.ntg.recruitment.entity.RecruitProcess;
import com.ntg.recruitment.service.RecruitProcessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/processes")
@RequiredArgsConstructor
public class RecruitProcessController {

    private final RecruitProcessService service;

    @PostMapping
    public ResponseEntity<ProcessDto> create(
            @RequestBody @Valid ProcessDto dto
    ) {
        return ResponseEntity.ok(service.createProcess(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProcessDto>> getAll() {
        return ResponseEntity.ok(service.getAllProcesses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProcessById(id));
    }

    @PatchMapping("/{id}/end")
    public ResponseEntity<Void> end(@PathVariable Long id) {
        service.endProcess(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<RecruitProcess>> getActive() {
        return ResponseEntity.ok(service.getActiveProcesses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProcess(id);
        return ResponseEntity.noContent().build();
    }


}
