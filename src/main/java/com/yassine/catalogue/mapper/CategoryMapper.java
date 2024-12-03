package com.yassine.catalogue.mapper;

import org.mapstruct.Mapper;

import com.yassine.catalogue.dto.request.CategoryRequestDto;
import com.yassine.catalogue.dto.res.CategoryResponseDto;
import com.yassine.catalogue.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto toResponseDto (Category category);
}
