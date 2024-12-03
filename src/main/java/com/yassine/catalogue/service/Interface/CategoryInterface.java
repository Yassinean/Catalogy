package com.yassine.catalogue.service.Interface;

import java.util.List;
import java.util.Optional;

import com.yassine.catalogue.dto.request.CategoryRequestDto;
import com.yassine.catalogue.dto.res.CategoryResponseDto;

public interface CategoryInterface {

    CategoryResponseDto create(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto update(Long id ,CategoryRequestDto categoryRequestDto);
    void delete(Long id);
    Optional<CategoryResponseDto> findById(Long id);
    List<CategoryResponseDto> findAll(); 

}
