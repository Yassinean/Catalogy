package com.yassine.catalogue.service.Implementation;

import java.util.List;

import com.yassine.catalogue.dto.request.UserRequestDto;
import org.springframework.stereotype.Service;

import com.yassine.catalogue.dto.res.UserResponseDto;
import com.yassine.catalogue.entities.User;
import com.yassine.catalogue.entities.Enum.Role;
import com.yassine.catalogue.mapper.UserMapper;
import com.yassine.catalogue.repository.UserRepository;
import com.yassine.catalogue.service.Interface.UserInterface;


@Service
public class UserImp implements UserInterface {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponseDto).toList();
    }

    @Override
    public UserResponseDto updateUserRole(Long id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Role.ROLE_ADMIN);
        User updatedUser = userRepository.save(user);

        return userMapper.toResponseDto(updatedUser);
    }
}