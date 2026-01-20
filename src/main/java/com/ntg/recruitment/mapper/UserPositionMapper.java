package com.ntg.recruitment.mapper;

import com.ntg.recruitment.dto.UserPositionDto;
import com.ntg.recruitment.entity.UserPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPositionMapper {
//
//    @Mapping(target = "user", ignore = true)
//    @Mapping(target = "position", ignore = true)
    UserPosition toEntity(UserPositionDto dto);
//
//    @Mapping(target = "userId", source = "user.id")
//    @Mapping(target = "positionId", source = "position.id")
    UserPositionDto toDto(UserPosition entity);

    List<UserPositionDto> toDtoList(List<UserPosition> entities);
}
