package com.yassine.catalogue.dto.res;

import java.util.List;

import com.yassine.catalogue.entities.Product;

public record CategoryResponseDto(
    String nom,
    String description,
    List<Product> productList
) {}
