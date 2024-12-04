package com.yassine.catalogue.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductRequestDto(
    @NotBlank String designation,
    @NotNull @Positive Double prix,
    @NotNull @Positive Integer quantity,
    Long categoryId
) {}
