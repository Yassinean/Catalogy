package com.yassine.catalogue.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
    @NotBlank String nom,
    @NotBlank String description
) {}
