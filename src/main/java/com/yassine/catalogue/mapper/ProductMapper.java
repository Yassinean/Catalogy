package com.yassine.catalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yassine.catalogue.dto.request.ProductRequestDto;
import com.yassine.catalogue.dto.res.ProductResponseDto;
import com.yassine.catalogue.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequestDto productRequestDto);

    ProductResponseDto toResponseDto(Product product);
}
