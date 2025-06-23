package com.example.kivicarebackend.dto.response;

import com.example.kivicarebackend.enums.product.ProductLabel;
import com.example.kivicarebackend.enums.product.ProductStatus;
import com.example.kivicarebackend.enums.product.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long productId;
    private ProductType productType;
    private String name;
    private String description;
    private BigDecimal price;
    private String unit;
    private ProductStatus productStatus;
    private Integer stockQuantities;
    private String imageUrl;
    private ProductLabel label;
}
