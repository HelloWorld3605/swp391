package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.ServiceFeatureRequest;
import com.example.kivicarebackend.dto.response.ServiceFeatureResponse;
import com.example.kivicarebackend.entity.ServiceFeature;
import com.example.kivicarebackend.mapper.ServiceFeatureMapper;
import com.example.kivicarebackend.repository.ServiceFeatureRepository;
import com.example.kivicarebackend.repository.ServiceRepository;
import com.example.kivicarebackend.service.ServiceFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceFeatureServiceImpl implements ServiceFeatureService {
    private final ServiceFeatureRepository serviceFeatureRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceFeatureMapper serviceFeatureMapper;

    @Override
    public List<ServiceFeatureResponse> getByServiceId(Long serviceId) {
        return serviceFeatureMapper.toResponseList(
                serviceFeatureRepository.findByService_ServiceId(serviceId)
        );
    }

    @Override
    public ServiceFeatureResponse create(ServiceFeatureRequest req) {
        com.example.kivicarebackend.entity.Service service = serviceRepository.findById(req.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));
        ServiceFeature entity = serviceFeatureMapper.toEntity(req, service);
        entity = serviceFeatureRepository.save(entity);
        return serviceFeatureMapper.toResponse(entity);
    }
}

