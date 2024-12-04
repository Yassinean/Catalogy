package com.yassine.catalogue.dto.res;

import lombok.Builder;

@Builder
public record ProductResponseDto(
    String designation,
    Double prix,
    Integer quantity
) {}
