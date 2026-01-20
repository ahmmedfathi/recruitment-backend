package com.ntg.recruitment.mapper;

import com.ntg.recruitment.dto.UserProcessDto;
import com.ntg.recruitment.entity.UserProcess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")

public interface UserProcessMapper {
    UserProcessDto toDto(UserProcess userProcess);

    List<UserProcessDto> toDtoList(List<UserProcess> userProcesses);

    // DTO → Entity

    UserProcess toEntity(UserProcessDto dto);

    // Update Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserProcessDto dto, @MappingTarget UserProcess entity);
}
