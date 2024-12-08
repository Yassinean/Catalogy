package com.yassine.catalogue.dto.res;

import lombok.Builder;

@Builder
public record UserResponseDto(
        Long id,
        String username,
        String email,
        Boolean active,
        String role) {

}
