package com.yassine.catalogue.dto.res;

import java.util.List;

import com.yassine.catalogue.entities.Product;

import lombok.Builder;

@Builder
public record CategoryResponseDto(
    String nom,
    String description,
    List<Product> productList
) {}
