package com.yassine.catalogue.service.Implementation;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.yassine.catalogue.dto.request.ProductRequestDto;
import com.yassine.catalogue.dto.res.ProductResponseDto;
import com.yassine.catalogue.entities.Category;
import com.yassine.catalogue.entities.Product;
import com.yassine.catalogue.exceptions.CategoryException;
import com.yassine.catalogue.exceptions.ProductException;
import com.yassine.catalogue.mapper.ProductMapper;
import com.yassine.catalogue.repository.CategoryRepository;
import com.yassine.catalogue.repository.ProductRepository;
import com.yassine.catalogue.service.Interface.ProductInterface;


@Service
public class ProductImp implements ProductInterface {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductImp(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.categoryId())
                .orElseThrow(() -> new CategoryException("Category not found"));

        Product product = productMapper.toEntity(productRequestDto);
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.toResponseDto(product);
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("product not found"));
        Category category = categoryRepository.findById(productRequestDto.categoryId())
                .orElseThrow(() -> new CategoryException("Category not found"));

        product.setDesignation(productRequestDto.designation());
        product.setPrice(productRequestDto.price());
        product.setQuantity(productRequestDto.quantity());
        product.setCategory(category);

        Product productUpdated = productRepository.save(product);
        return productMapper.toResponseDto(productUpdated);

    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductException("Product not found");
        }
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

    @Override
    public List<ProductResponseDto> findByCategoryId(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId).stream()
                .map(productMapper::toResponseDto)
                .toList();
    }
}
