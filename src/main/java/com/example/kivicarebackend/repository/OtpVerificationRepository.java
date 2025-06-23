package com.example.kivicarebackend.repository;

import com.example.kivicarebackend.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
    Optional<OtpVerification> findByEmailAndOtpAndVerifiedFalse(String email, String otp);
}
