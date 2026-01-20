package com.ntg.recruitment.mapper;

import com.ntg.recruitment.dto.UserDto;
import com.ntg.recruitment.dto.UserDtoResponse;
import com.ntg.recruitment.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // لتحويل من DTO لـ Entity
    User toEntity(UserDto dto);

    // لتحويل من Entity لـ DTO
    UserDto toDto(User user);

    // لتحديث Entity موجود من DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserDto dto, @MappingTarget User user);
}

