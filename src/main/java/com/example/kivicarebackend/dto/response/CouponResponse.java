package com.example.kivicarebackend.dto.response;

import com.example.kivicarebackend.enums.coupon.DiscountType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CouponResponse {
    private String code;
    private String description;
    private DiscountType discountType;
    private BigDecimal value;
    private LocalDate expirationDate;
}
