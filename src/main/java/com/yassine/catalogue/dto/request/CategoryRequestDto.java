package com.yassine.catalogue.dto.request;

import java.util.List;

import com.yassine.catalogue.entities.Product;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
    @NotBlank String nom,
    @NotBlank String description,
    List<Product> productList
) {}
