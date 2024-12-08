package com.yassine.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yassine.catalogue.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
