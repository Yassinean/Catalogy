package com.yassine.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.catalogue.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
