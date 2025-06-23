package com.example.kivicarebackend.dto.request;

import com.example.kivicarebackend.enums.product.ProductLabel;
import com.example.kivicarebackend.enums.product.ProductStatus;
import com.example.kivicarebackend.enums.product.ProductType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotNull
    private ProductType productType;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    @NotBlank
    private String unit;

    @NotNull
    private ProductStatus productStatus;

    @NotNull
    @Min(0)
    private Integer stockQuantities;

    private String imageUrl;

    @NotNull
    private ProductLabel label;
}
