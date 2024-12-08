package com.yassine.catalogue.controller;

import com.yassine.catalogue.dto.request.UserRequestDto;
import com.yassine.catalogue.entities.User;
import com.yassine.catalogue.mapper.UserMapper;
import com.yassine.catalogue.service.Interface.UserInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserInterface userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AuthController(AuthenticationManager authenticationManager, UserInterface userService, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto) {
        if (userService.existsByUsername(userRequestDto.username())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username is already taken.");
        }

        String  encodePassword = passwordEncoder.encode(userRequestDto.password());
        User user = userMapper.toEntity(userRequestDto);
        user.setUsername(userRequestDto.username());
        user.setPassword(encodePassword);
        user.setActive(true);

        userService.saveUser(user);
//        log.info("User registered successfully with username: {}", userRequestDto.username());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully.");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated UserRequestDto loginRequest, HttpServletRequest request) {
        try {
            request.getSession(false);
            request.getSession(true);

            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),
                            loginRequest.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

//            log.info("User logged in successfully: {}", request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
            return ResponseEntity.ok("User logged in successfully.");
        } catch (Exception e) {
//            log.error("Login failed for user: {}. Error: {}", loginRequest.username(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


}