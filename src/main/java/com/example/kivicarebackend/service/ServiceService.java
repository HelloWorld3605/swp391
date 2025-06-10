package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.ServiceRequest;
import com.example.kivicarebackend.dto.response.ServiceResponse;

import java.util.List;

public interface ServiceService {
    ServiceResponse getById(Long id);
    List<ServiceResponse> getAll();
    ServiceResponse create(ServiceRequest req);
}

