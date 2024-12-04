package com.yassine.catalogue.service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yassine.catalogue.dto.request.CategoryRequestDto;
import com.yassine.catalogue.dto.res.CategoryResponseDto;
import com.yassine.catalogue.entities.Category;
import com.yassine.catalogue.exceptions.CategoryException;
import com.yassine.catalogue.mapper.CategoryMapper;
import com.yassine.catalogue.repository.CategoryRepository;
import com.yassine.catalogue.service.Interface.CategoryInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryImp implements CategoryInterface {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        categoryRepository.save(category);
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException("Category not found"));

        category.setNom(categoryRequestDto.nom());
        category.setDescription(categoryRequestDto.description());

        Category categoryUpdated = categoryRepository.save(category);
        return categoryMapper.toResponseDto(categoryUpdated);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryResponseDto> findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toResponseDto);
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponseDto)
                .toList();
    }

}
