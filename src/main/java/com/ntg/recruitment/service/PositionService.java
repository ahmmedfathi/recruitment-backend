package com.ntg.recruitment.service;

import com.ntg.recruitment.dto.PositionDto;
import com.ntg.recruitment.entity.Position;
import com.ntg.recruitment.mapper.PositionMapper;
import com.ntg.recruitment.repo.PositionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepo positionRepo;
    private final PositionMapper positionMapper;



    public List<PositionDto> getAllPositionsOrderByCreatedAtDto() {
        return positionMapper.toDtoList(positionRepo.findAllOrderByCreatedAtDesc());
    }

    public PositionDto getPositionDtoById(Long id) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        return positionMapper.toDto(position);
    }

    public List<PositionDto> searchByNameDto(String name) {
        List<Position> positions = positionRepo.findByNameContainingIgnoreCase(name);
        return positionMapper.toDtoList(positions);
    }

    public Position createPosition(PositionDto dto) {
        Position position = positionMapper.toEntity(dto);
        return positionRepo.save(position);

    }

    public void deletePosition(Long id) {
        if (!positionRepo.existsById(id)) {
            throw new RuntimeException("Position not found");
        }
        positionRepo.deleteById(id);
    }

    public List<Position> getAllPositions() {
        return positionRepo.findAll();
    }


    public List<Position> getAllPositionsOrderByCreatedAt() {
        return positionRepo.findAllOrderByCreatedAtDesc();
    }

    public Position getPositionById(Long id) {
        return positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
    }

    public List<Position> searchByName(String name) {
        return positionRepo.findByNameContainingIgnoreCase(name);
    }

    public PositionDto updatePosition(Long id, PositionDto dto) {
        Position existing = positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getExperience() >= 0) existing.setExperience(dto.getExperience());
        if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
        if (dto.getLocation() != null) existing.setLocation(dto.getLocation());
        if (dto.getReportsTo() != null) existing.setReportsTo(dto.getReportsTo());
        if (dto.getExpireAt() != null) existing.setExpireAt(
                dto.getExpireAt().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        );
        if (dto.getSkills() != null) existing.setSkills(dto.getSkills());
        if (dto.getResponsibilities() != null) existing.setResponsibilities(dto.getResponsibilities());

        Position updated = positionRepo.save(existing);

        return positionMapper.toDto(updated);
    }
}