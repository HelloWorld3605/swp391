package com.example.kivicarebackend.entity;

import com.example.kivicarebackend.enums.coupon.DiscountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "coupons")
@Getter
@Setter
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type")
    private DiscountType discountType; // PERCENTAGE, FIXED

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "expiration_date")
    private java.time.LocalDate expirationDate;

}

