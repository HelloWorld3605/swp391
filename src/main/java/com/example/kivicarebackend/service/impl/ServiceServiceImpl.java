package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.ServiceRequest;
import com.example.kivicarebackend.dto.response.ServiceResponse;
import com.example.kivicarebackend.entity.Department;
import com.example.kivicarebackend.mapper.ServiceMapper;
import com.example.kivicarebackend.repository.DepartmentRepository;
import com.example.kivicarebackend.repository.ServiceRepository;
import com.example.kivicarebackend.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final DepartmentRepository departmentRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ServiceResponse getById(Long id) {
        com.example.kivicarebackend.entity.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return serviceMapper.toResponse(service);
    }

    @Override
    public List<ServiceResponse> getAll() {
        return serviceMapper.toResponseList(serviceRepository.findAll());
    }

    @Override
    public ServiceResponse create(ServiceRequest req) {
        Department dept = departmentRepository.findById(req.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        com.example.kivicarebackend.entity.Service entity = serviceMapper.toEntity(req, dept);
        entity = serviceRepository.save(entity);
        return serviceMapper.toResponse(entity);
    }
}

