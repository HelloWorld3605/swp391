package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.HospitalRequest;
import com.example.kivicarebackend.dto.response.HospitalResponse;
import com.example.kivicarebackend.entity.Hospital;
import com.example.kivicarebackend.mapper.HospitalMapper;
import com.example.kivicarebackend.repository.HospitalRepository;
import com.example.kivicarebackend.service.HospitalService;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final HospitalMapper hospitalMapper;

    @Override
    public List<HospitalResponse> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitalMapper.toResponseList(hospitals);
    }


    @Override
    public HospitalResponse createHospital(HospitalRequest request) {
        Hospital hospital = hospitalMapper.toEntity(request);
        hospital = hospitalRepository.save(hospital);
        return hospitalMapper.toResponse(hospital);
    }

    @Override
    public HospitalResponse updateHospital(Long id, HospitalRequest request) {
        Hospital existing = hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Hospital updated = hospitalMapper.toEntity(request);
        updated.setHospitalId(id);

        updated = hospitalRepository.save(updated);
        return hospitalMapper.toResponse(updated);
    }

    @Override
    public HospitalResponse getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));
        return hospitalMapper.toResponse(hospital);
    }

    @Override
    public PageResponse<HospitalResponse> getAllHospitals(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Hospital> hospitalPage = hospitalRepository.findAll(pageable);
        Page<HospitalResponse> mappedPage = hospitalPage.map(hospitalMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }
}
