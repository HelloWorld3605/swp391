package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.HospitalRequest;
import com.example.kivicarebackend.dto.response.HospitalResponse;
import com.example.kivicarebackend.util.PageResponse;

import java.util.List;

public interface HospitalService {
    List<HospitalResponse> getAllHospitals();

    HospitalResponse createHospital(HospitalRequest request);

    HospitalResponse updateHospital(Long id, HospitalRequest request);

    HospitalResponse getHospitalById(Long id);

    PageResponse<HospitalResponse> getAllHospitals(int page, int size);
}
