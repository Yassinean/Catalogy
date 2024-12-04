package com.yassine.catalogue.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
        @NotBlank String nom,
        @NotBlank String description,
        List<Long> productIds) {
}
