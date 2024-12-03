package com.yassine.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.catalogue.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
