package com.yassine.catalogue.dto.res;

import java.util.List;

import lombok.Builder;

@Builder
public record CategoryResponseDto(
    String nom,
    String description,
    List<Long> productIds
) {}
