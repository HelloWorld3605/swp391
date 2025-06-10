package com.example.kivicarebackend.repository;

import com.example.kivicarebackend.entity.ServiceFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceFeatureRepository extends JpaRepository<ServiceFeature, Long> {
    List<ServiceFeature> findByService_ServiceId(Long serviceId);
}

