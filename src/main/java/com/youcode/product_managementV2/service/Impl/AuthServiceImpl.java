package com.youcode.product_managementV2.service.Impl;

import com.youcode.product_managementV2.Entity.User;
import com.youcode.product_managementV2.Entity.UserRole;
import com.youcode.product_managementV2.dto.request.UserRequestDto;
import com.youcode.product_managementV2.dto.response.UserResponseDto;
import com.youcode.product_managementV2.dto.update.UserRoleUpdateDto;
import com.youcode.product_managementV2.mapper.UserMapper;
import com.youcode.product_managementV2.repository.UserRepository;
import com.youcode.product_managementV2.repository.UserRoleRepository;
import com.youcode.product_managementV2.service.Interface.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final HttpSession httpSession;

    public AuthServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, UserMapper userMapper, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.httpSession = httpSession;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);

        Optional<UserRole> role = userRoleRepository.findByRoleName(userRequestDto.getRoleName());
        if (role.isEmpty()) {
            throw new IllegalArgumentException("Invalid role: " + userRequestDto.getRoleName());
        }
        user.setRole(role.get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setActive(true);

        userRepository.save(user);

        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto login(UserRequestDto userRequestDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userRequestDto.getLogin(), userRequestDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(userRequestDto.getLogin());

            User user = userRepository.findByLogin(userRequestDto.getLogin())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + userRequestDto.getLogin()));

            String sessionId = httpSession.getId();
            httpSession.setAttribute("user_id", user.getId());
            httpSession.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            log.info("User logged in successfully: {}", sessionId);

            System.out.println("User logged in successfully: {" + sessionId + "}");

            UserResponseDto userResponseDto = userMapper.toResponseDto(user);
            userResponseDto.setSessionId(sessionId);

            return userResponseDto;
        } catch (Exception e) {
            log.error("Login failed for user: {}. Error: {}", userRequestDto.getLogin(), e.getMessage());
            throw new IllegalArgumentException("Invalid credentials");
        }
    }


    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        return userMapper.toResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUserRole(Long userId, UserRoleUpdateDto userRoleUpdateDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        UserRole role = userRoleRepository.findById(userRoleUpdateDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);


        User updatedUser = userRepository.save(user);

        return UserMapper.INSTANCE.toResponseDto(updatedUser);
    }
}



