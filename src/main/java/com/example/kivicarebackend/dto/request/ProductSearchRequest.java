package com.example.kivicarebackend.dto.request;

import com.example.kivicarebackend.enums.product.ProductLabel;
import com.example.kivicarebackend.enums.product.ProductStatus;
import com.example.kivicarebackend.enums.product.ProductType;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class ProductSearchRequest {
    private String keyword;         // tìm theo name hoặc description
    private ProductType productType;
    private ProductStatus productStatus;
    private ProductLabel label;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private String unit;
}
