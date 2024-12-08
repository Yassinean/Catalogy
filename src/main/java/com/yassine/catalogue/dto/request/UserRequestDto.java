package com.yassine.catalogue.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRequestDto(
        @NotBlank(message = "this field should not be empty") String username,
        @NotBlank(message = "this field should not be empty") String password,
        boolean active,
        @NotBlank(message = "this field should not be empty") String role) {

}
