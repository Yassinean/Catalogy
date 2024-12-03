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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yassine.catalogue.dto.request.CategoryRequestDto;
import com.yassine.catalogue.dto.res.CategoryResponseDto;
import com.yassine.catalogue.mapper.CategoryMapper;
import com.yassine.catalogue.service.Interface.CategoryInterface;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/categories")
public class CategoryController {

    private final CategoryInterface categoryInterface;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@Validated @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryInterface.create(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(
                                                        @PathVariable Long id,
                                                        @Validated @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto updateCategoryResponseDto = categoryInterface.update(id,categoryRequestDto);
        return ResponseEntity.ok(updateCategoryResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<CategoryResponseDto> delete(@PathVariable Long id){
        categoryInterface.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id){
        return categoryInterface.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll(){
        List<CategoryResponseDto> categories = categoryInterface.findAll();
        return ResponseEntity.ok(categories);
    }

}
