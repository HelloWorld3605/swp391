package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.StaffRequest;
import com.example.kivicarebackend.dto.request.StaffSearchRequest;
import com.example.kivicarebackend.dto.response.StaffResponse;
import com.example.kivicarebackend.entity.Staff;
import com.example.kivicarebackend.mapper.StaffMapper;
import com.example.kivicarebackend.repository.StaffRepository;
import com.example.kivicarebackend.service.StaffService;
import com.example.kivicarebackend.specification.StaffSpecification;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    public StaffResponse createStaff(StaffRequest request) {
        Staff staff = staffMapper.toEntity(request);
        staff = staffRepository.save(staff);
        return staffMapper.toResponse(staff);
    }



    @Override
    public PageResponse<StaffResponse> getAllStaffPaged(int page, int size) {
        Page<Staff> staffPage = staffRepository.findAll(PageRequest.of(page, size));
        Page<StaffResponse> mappedPage = staffPage.map(staffMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public StaffResponse getStaffById(Long id) {
        return staffRepository.findById(id)
                .map(staffMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
    }
    @Override
    public StaffResponse updateStaff(Long id, StaffRequest request) {
        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Staff updated = staffMapper.toEntity(request);
        updated.setStaffId(id);
        staffRepository.save(updated);
        return staffMapper.toResponse(updated);
    }

    @Override
    public PageResponse<StaffResponse> search(StaffSearchRequest request, int page, int size) {
        Specification<Staff> spec = (root, query, cb) -> cb.conjunction();

        if (request.getUserId() != null)
            spec = spec.and(StaffSpecification.hasUserId(request.getUserId()));
        if (request.getKeyword() != null && !request.getKeyword().isBlank())
            spec = spec.and(StaffSpecification.hasKeyword(request.getKeyword()));
        if (request.getFullName() != null)
            spec = spec.and(StaffSpecification.hasFullName(request.getFullName()));
        if (request.getHospitalId() != null)
            spec = spec.and(StaffSpecification.hasHospitalId(request.getHospitalId()));
        if (request.getDepartmentId() != null)
            spec = spec.and(StaffSpecification.hasDepartmentId(request.getDepartmentId()));
        if (request.getRankLevel() != null)
            spec = spec.and(StaffSpecification.hasRankLevel(request.getRankLevel()));

        Page<Staff> staffPage = staffRepository.findAll(spec, PageRequest.of(page, size));
        Page<StaffResponse> mappedPage = staffPage.map(staffMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }



}
