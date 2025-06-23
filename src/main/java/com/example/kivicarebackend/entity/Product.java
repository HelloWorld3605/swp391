package com.example.kivicarebackend.entity;

import com.example.kivicarebackend.enums.product.ProductLabel;
import com.example.kivicarebackend.enums.product.ProductStatus;
import com.example.kivicarebackend.enums.product.ProductType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType = ProductType.MEDICAL_PRODUCT;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "unit", nullable = false)
    private String unit;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false)
    private ProductStatus productStatus = ProductStatus.ACTIVE;

    @Column(name = "stock_quantities", nullable = false)
    private int stockQuantities;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "label", nullable = false)
    private ProductLabel label = ProductLabel.STANDARD;
}
