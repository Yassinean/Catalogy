package com.yassine.catalogue.dto.res;

import lombok.Builder;

@Builder
public record ProductResponseDto(
    String designation,
    Double price,
    Integer quantity,
    Long categoryId,
    Long userId,
    String categoryNom,
    String userNom
) {}
