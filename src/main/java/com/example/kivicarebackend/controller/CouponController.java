package com.example.kivicarebackend.controller;

import com.example.kivicarebackend.dto.request.CouponRequest;
import com.example.kivicarebackend.dto.response.CouponResponse;
import com.example.kivicarebackend.service.CouponService;
import com.example.kivicarebackend.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    // Lấy tất cả các coupon
    @GetMapping
    public ResponseEntity<PageResponse<CouponResponse>> getCoupons(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageResponse<CouponResponse> couponPage = couponService.getCouponsWithPagination(page, size);
        return new ResponseEntity<>(couponPage, HttpStatus.OK);
    }

    // Lấy thông tin một coupon theo id
    @GetMapping("/{couponId}")
    public ResponseEntity<CouponResponse> getCouponById(@PathVariable Long couponId) {
        CouponResponse couponResponse = couponService.getCouponById(couponId);
        return new ResponseEntity<>(couponResponse, HttpStatus.OK);
    }

    // Tạo mới một coupon
    @PostMapping
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponRequest couponRequest) {
        CouponResponse couponResponse = couponService.createCoupon(couponRequest);
        return new ResponseEntity<>(couponResponse, HttpStatus.CREATED);
    }

    // Cập nhật một coupon
    @PutMapping("/{couponId}")
    public ResponseEntity<CouponResponse> updateCoupon(
            @PathVariable Long couponId, @RequestBody CouponRequest couponRequest) {
        CouponResponse couponResponse = couponService.updateCoupon(couponId, couponRequest);
        return new ResponseEntity<>(couponResponse, HttpStatus.OK);
    }

    // Xóa một coupon
    @DeleteMapping("/{couponId}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long couponId) {
        couponService.deleteCoupon(couponId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
