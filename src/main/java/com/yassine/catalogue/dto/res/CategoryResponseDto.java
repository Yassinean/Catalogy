package com.yassine.catalogue.dto.res;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

@Builder
public record CategoryResponseDto(
                String nom,
                String description,
                List<ProductResponseDto> products) {
        public CategoryResponseDto {
                products = (products == null) ? new ArrayList<>() : products;
        }
}
