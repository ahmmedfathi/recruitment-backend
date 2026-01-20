package com.ntg.recruitment.mapper;

import com.ntg.recruitment.dto.PositionDto;
import com.ntg.recruitment.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

//    @Mapping(target = "id", ignore = true) // id auto-generated
    Position toEntity(PositionDto dto);

    PositionDto toDto(Position entity);

    List<PositionDto> toDtoList(List<Position> entities);
}

