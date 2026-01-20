package com.ntg.recruitment.mapper;

import com.ntg.recruitment.dto.ProcessDto;
import com.ntg.recruitment.entity.RecruitProcess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecruitProcessMapper {

    RecruitProcess toEntity(ProcessDto dto);

//    @Mapping(source = "name", target = "name")
    ProcessDto toDto(RecruitProcess process);

    List<ProcessDto> toDtoList(List<RecruitProcess> processes);
}
