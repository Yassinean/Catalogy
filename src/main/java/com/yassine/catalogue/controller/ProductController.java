package com.yassine.catalogue.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yassine.catalogue.dto.request.ProductRequestDto;
import com.yassine.catalogue.dto.res.ProductResponseDto;
import com.yassine.catalogue.service.Interface.ProductInterface;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductInterface productInterface;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Validated @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productInterface.create(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable Long id,
            @Validated @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto updatedProductResponseDto = productInterface.update(id, productRequestDto);
        return ResponseEntity.ok(updatedProductResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productInterface.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        return productInterface.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        List<ProductResponseDto> products = productInterface.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDto>> findByCategoryId(@PathVariable Long categoryId) {
        List<ProductResponseDto> products = productInterface.findByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }
}
