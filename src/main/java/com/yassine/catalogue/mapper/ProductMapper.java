package com.yassine.catalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yassine.catalogue.dto.request.ProductRequestDto;
import com.yassine.catalogue.dto.res.ProductResponseDto;
import com.yassine.catalogue.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category.id", source = "categoryId")
    Product toEntity(ProductRequestDto productRequestDto);

    @Mapping(target = "categoryNom", source = "category.nom")
    ProductResponseDto toResponseDto(Product product);
}
