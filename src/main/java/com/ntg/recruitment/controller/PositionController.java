package com.ntg.recruitment.controller;

import com.ntg.recruitment.dto.PositionDto;
import com.ntg.recruitment.entity.Position;
import com.ntg.recruitment.security.CustomUserDetails;
import com.ntg.recruitment.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {


    private final PositionService positionService;


    @GetMapping
    public ResponseEntity<List<Position>> getAll() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }


    @GetMapping("/latest")
    public ResponseEntity<List<PositionDto>> getLatest() {
        return ResponseEntity.ok(positionService.getAllPositionsOrderByCreatedAtDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(positionService.getPositionDtoById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PositionDto>> search(@RequestParam String name) {
        return ResponseEntity.ok(positionService.searchByNameDto(name));
    }

    @PostMapping
    public Position createPosition(@RequestBody @Valid PositionDto dto) {

        return positionService.createPosition(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id
    ,            @AuthenticationPrincipal CustomUserDetails currentUser

    ) {
        positionService.deletePosition(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePosition(
            @PathVariable Long id,
            @RequestBody PositionDto dto
    ) {
        PositionDto updated = positionService.updatePosition(id, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Position updated successfully");
        response.put("position", updated);

        return ResponseEntity.ok(response);
    }

}