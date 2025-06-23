package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.CouponRequest;
import com.example.kivicarebackend.dto.response.CouponResponse;
import com.example.kivicarebackend.entity.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    Coupon toEntity(CouponRequest request);

    CouponResponse toResponse(Coupon coupon);
}
