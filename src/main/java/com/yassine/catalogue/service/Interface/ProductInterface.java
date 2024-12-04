package com.yassine.catalogue.service.Interface;

import java.util.List;
import java.util.Optional;

import com.yassine.catalogue.dto.request.ProductRequestDto;
import com.yassine.catalogue.dto.res.ProductResponseDto;

public interface ProductInterface {

    ProductResponseDto create(ProductRequestDto productRequestDto);
    ProductResponseDto update(Long id ,ProductRequestDto productRequestDto);
    void delete(Long id);
    Optional<ProductResponseDto> findById(Long id);
    List<ProductResponseDto> findAll(); 
    List<ProductResponseDto> findByCategoryId(Long id); 

}
