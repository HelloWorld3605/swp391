package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.CouponRequest;
import com.example.kivicarebackend.dto.response.CouponResponse;
import com.example.kivicarebackend.util.PageResponse;

import java.util.List;

public interface CouponService {
    // Lấy tất cả các coupon
    List<CouponResponse> getAllCoupons();

    // Lấy thông tin một coupon theo id
    CouponResponse getCouponById(Long couponId);

    // Tạo mới một coupon
    CouponResponse createCoupon(CouponRequest couponRequest);

    // Cập nhật coupon
    CouponResponse updateCoupon(Long couponId, CouponRequest couponRequest);

    // Xóa một coupon
    void deleteCoupon(Long couponId);

    // All coupon with paginnation
    PageResponse<CouponResponse> getCouponsWithPagination(int page, int size);
}
