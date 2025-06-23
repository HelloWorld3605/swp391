package com.example.kivicarebackend.dto.request;

import com.example.kivicarebackend.enums.coupon.DiscountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CouponRequest {
    @NotBlank
    private String code;

    @NotBlank
    private String description;

    @NotBlank
    private DiscountType discountType;

    @NotNull
    private BigDecimal value;

    @NotNull
    private LocalDate expirationDate;
}
