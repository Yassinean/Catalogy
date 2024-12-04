package com.yassine.catalogue.service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yassine.catalogue.dto.request.ProductRequestDto;
import com.yassine.catalogue.dto.res.ProductResponseDto;
import com.yassine.catalogue.entities.Product;
import com.yassine.catalogue.exceptions.ProductException;
import com.yassine.catalogue.mapper.ProductMapper;
import com.yassine.catalogue.repository.ProductRepository;
import com.yassine.catalogue.service.Interface.ProductInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImp implements ProductInterface{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = productMapper.toEntity(productRequestDto);
        productRepository.save(product);
        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto update(Long id , ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductException("product not found"));

        product.setDesignation(productRequestDto.designation());
        product.setPrix(productRequestDto.prix());
        product.setQuantity(productRequestDto.quantity());

        Product productUpdated = productRepository.save(product);
        return productMapper.toResponseDto(productUpdated);
        
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductResponseDto> findById(Long id) {
        return productRepository.findById(id).map(productMapper::toResponseDto);        
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toResponseDto).toList();
    }
}
