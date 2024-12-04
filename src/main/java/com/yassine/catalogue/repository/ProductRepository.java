package com.yassine.catalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.catalogue.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategorie_Id(Long categoryId);
}
