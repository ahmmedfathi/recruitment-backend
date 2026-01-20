package com.ntg.recruitment.mapper;

import com.ntg.recruitment.dto.StatusDto;
import com.ntg.recruitment.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    // Entity → DTO
    StatusDto toDto(Status status);

    List<StatusDto> toDtoList(List<Status> statuses);

    // DTO → Entity
    Status toEntity(StatusDto dto);
}
