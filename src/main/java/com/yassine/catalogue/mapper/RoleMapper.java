package com.yassine.catalogue.mapper;

import com.yassine.catalogue.dto.request.UserRequestDto;
import com.yassine.catalogue.dto.res.UserResponseDto;
import com.yassine.catalogue.entities.User;

public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(User user);
}
