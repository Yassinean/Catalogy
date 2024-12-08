package com.yassine.catalogue.service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yassine.catalogue.dto.request.UserRequestDto;
import com.yassine.catalogue.dto.res.UserResponseDto;
import com.yassine.catalogue.entities.Role;
import com.yassine.catalogue.entities.User;
import com.yassine.catalogue.exceptions.UserException;
import com.yassine.catalogue.mapper.UserMapper;
import com.yassine.catalogue.repository.RoleRepository;
import com.yassine.catalogue.repository.UserRepository;
import com.yassine.catalogue.service.Interface.UserInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserImp implements UserInterface {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        Role role = roleRepository.findByName(userRequestDto.role())
                .orElseThrow(() -> new UserException("Role not found"));

        User user = userMapper.toEntity(userRequestDto);
        user.setRole(role);
        userRepository.save(user);
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found"));

        Role role = roleRepository.findByName(userRequestDto.role())
                .orElseThrow(() -> new UserException("Role not found"));

        user.setUsername(userRequestDto.username());
        user.setRole(role);

        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toResponseDto);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponseDto).toList();
    }

    @Override
    public Optional<UserResponseDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toResponseDto);
    }
}