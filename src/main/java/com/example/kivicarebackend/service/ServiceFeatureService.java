package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.ServiceFeatureRequest;
import com.example.kivicarebackend.dto.response.ServiceFeatureResponse;

import java.util.List;

public interface ServiceFeatureService {
    List<ServiceFeatureResponse> getByServiceId(Long serviceId);
    ServiceFeatureResponse create(ServiceFeatureRequest req);
}
