package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.StaffRequest;
import com.example.kivicarebackend.dto.request.StaffSearchRequest;
import com.example.kivicarebackend.dto.response.StaffResponse;
import com.example.kivicarebackend.util.PageResponse;


public interface StaffService {
    StaffResponse createStaff(StaffRequest request);
    PageResponse<StaffResponse> getAllStaffPaged(int page, int size);
    StaffResponse getStaffById(Long id);
    PageResponse<StaffResponse> search(StaffSearchRequest request, int page, int size);
    StaffResponse updateStaff(Long id, StaffRequest request);
    void deleteStaff(Long id);
}
