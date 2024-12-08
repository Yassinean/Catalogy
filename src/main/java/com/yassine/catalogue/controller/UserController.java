package com.yassine.catalogue.controller;

import java.util.List;

import com.yassine.catalogue.dto.res.UserResponseDto;
import com.yassine.catalogue.service.Interface.UserInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> listUsers() {
        List<UserResponseDto> users = userInterface.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/admin/users/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> updateRoles(
            @PathVariable Long id,
            @RequestParam String role) {
        UserResponseDto updatedUser = userInterface.updateUserRole(id, role);
        return ResponseEntity.ok(updatedUser);
    }
}