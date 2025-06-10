package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.DepartmentRequest;
import com.example.kivicarebackend.dto.response.DepartmentResponse;
import com.example.kivicarebackend.util.PageResponse;


import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest request);
    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);
    List<DepartmentResponse> getAllDepartmentsWithStats();
    DepartmentResponse getDepartmentById(Long id);
    PageResponse<DepartmentResponse> getDepartmentsWithStatsPaged(int page, int size);
}
