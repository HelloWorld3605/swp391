package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.CouponRequest;
import com.example.kivicarebackend.dto.response.CouponResponse;
import com.example.kivicarebackend.entity.Coupon;
import com.example.kivicarebackend.mapper.CouponMapper;
import com.example.kivicarebackend.repository.CouponRepository;
import com.example.kivicarebackend.service.CouponService;
import com.example.kivicarebackend.util.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponMapper  couponMapper;

    @Override
    public List<CouponResponse> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        return coupons.stream()
                .map(couponMapper::toResponse)  // Chuyển đổi từ Coupon entity sang CouponResponse
                .collect(Collectors.toList());
    }

    @Override
    public CouponResponse getCouponById(Long couponId) {
        Optional<Coupon> coupon = couponRepository.findById(couponId);
        if (coupon.isPresent()) {
            return couponMapper.toResponse(coupon.get());  // Trả về CouponResponse
        } else {
            throw new RuntimeException("Coupon not found");  // Nếu không tìm thấy coupon
        }
    }

    @Override
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        // Chuyển đổi CouponRequest thành Coupon entity
        Coupon coupon = couponMapper.toEntity(couponRequest);
        Coupon savedCoupon = couponRepository.save(coupon);  // Lưu coupon vào database
        return couponMapper.toResponse(savedCoupon);  // Trả về CouponResponse sau khi lưu
    }

    @Override
    public CouponResponse updateCoupon(Long couponId, CouponRequest couponRequest) {
        Optional<Coupon> couponOptional = couponRepository.findById(couponId);
        if (couponOptional.isPresent()) {
            Coupon coupon = couponOptional.get();
            // Cập nhật các thuộc tính của coupon
            coupon.setCode(couponRequest.getCode());
            coupon.setDescription(couponRequest.getDescription());
            coupon.setDiscountType(couponRequest.getDiscountType());
            coupon.setValue(couponRequest.getValue());
            coupon.setExpirationDate(couponRequest.getExpirationDate());

            // Lưu cập nhật vào database
            Coupon updatedCoupon = couponRepository.save(coupon);
            return couponMapper.toResponse(updatedCoupon);
        } else {
            throw new RuntimeException("Coupon not found");  // Nếu không tìm thấy coupon để cập nhật
        }
    }

    @Override
    public void deleteCoupon(Long couponId) {
        Optional<Coupon> coupon = couponRepository.findById(couponId);
        if (coupon.isPresent()) {
            couponRepository.delete(coupon.get());  // Xóa coupon khỏi database
        } else {
            throw new RuntimeException("Coupon not found");  // Nếu không tìm thấy coupon để xóa
        }
    }

    @Override
    public PageResponse<CouponResponse> getCouponsWithPagination(int page, int size) {
        // Tạo PageRequest để phân trang
        PageRequest pageRequest = PageRequest.of(page, size);

        // Lấy kết quả phân trang từ repository
        Page<Coupon> couponPage = couponRepository.findAll(pageRequest);

        // Chuyển đổi thành PageResponse với dữ liệu từ CouponResponse
        return new PageResponse<>(couponPage.map(couponMapper::toResponse));
    }
}
