package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.DoctorRequest;
import com.example.kivicarebackend.dto.response.DoctorResponse;
import com.example.kivicarebackend.util.PageResponse;


public interface DoctorService {
    DoctorResponse createDoctor(DoctorRequest request);
    PageResponse<DoctorResponse> getAllDoctorsPaged(int page, int size);
    DoctorResponse getDoctorById(Long id);
    DoctorResponse updateDoctor(Long id, DoctorRequest request);
}
