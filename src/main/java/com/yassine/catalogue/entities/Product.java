package com.yassine.catalogue.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
