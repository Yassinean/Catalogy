package com.yassine.catalogue.mapper;

import com.yassine.catalogue.dto.request.UserRequestDto;
import com.yassine.catalogue.dto.res.UserResponseDto;
import com.yassine.catalogue.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(User user);
}
