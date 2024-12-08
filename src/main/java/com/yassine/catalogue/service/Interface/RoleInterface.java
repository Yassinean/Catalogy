package com.yassine.catalogue.service.Interface;

import java.util.List;
import java.util.Optional;

import com.yassine.catalogue.dto.request.UserRequestDto;
import com.yassine.catalogue.dto.res.UserResponseDto;

public interface UserInterface {
  UserResponseDto create(UserRequestDto userRequestDto);

    UserResponseDto update(Long id, UserRequestDto userRequestDto);

    void delete(Long id);

    Optional<UserResponseDto> findById(Long id);

    List<UserResponseDto> findAll();

    Optional<UserResponseDto> findByUsername(String username);
}
