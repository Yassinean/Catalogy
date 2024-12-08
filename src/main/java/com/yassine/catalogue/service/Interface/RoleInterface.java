package com.yassine.catalogue.service.Interface;

import java.util.List;
import com.yassine.catalogue.dto.res.UserResponseDto;
import com.yassine.catalogue.entities.User;

public interface RoleInterface {
  List<UserResponseDto> findAll();

  UserResponseDto updateUserRole(Long id, String role);

  void saveUser(User user);

  boolean existsByUsername(String username);

}
