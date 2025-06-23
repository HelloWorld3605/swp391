package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.DepartmentRequest;
import com.example.kivicarebackend.dto.response.DepartmentResponse;
import com.example.kivicarebackend.entity.Department;
import com.example.kivicarebackend.entity.Hospital;
import com.example.kivicarebackend.entity.Staff;
import com.example.kivicarebackend.mapper.DepartmentMapper;
import com.example.kivicarebackend.repository.DepartmentRepository;
import com.example.kivicarebackend.repository.HospitalRepository;
import com.example.kivicarebackend.repository.StaffRepository;
import com.example.kivicarebackend.service.DepartmentService;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final StaffRepository staffRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Staff manager = staffRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Department department = departmentMapper.toEntity(request, manager, hospital);
        department = departmentRepository.save(department);
        return departmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Staff manager = staffRepository.findById(request.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Department updated = departmentMapper.toEntity(request, manager, hospital);
        updated.setDepartmentId(existing.getDepartmentId());

        departmentRepository.save(updated);
        return departmentMapper.toResponse(updated);
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return departmentMapper.toResponse(dept);
    }

    @Override
    public List<DepartmentResponse> findByHospitalId(Long hospitalId) {
        List<Department> departments = departmentRepository.findByHospital_HospitalId(hospitalId);
        return departments.stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<DepartmentResponse> getDepartmentsWithStatsPaged(int page, int size, Long hospitalId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departmentPage;
        if (hospitalId != null) {
            departmentPage = departmentRepository.findByHospital_HospitalId(hospitalId, pageable);
        } else {
            departmentPage = departmentRepository.findAll(pageable);
        }

        List<DepartmentRepository.DepartmentStats> statsList = departmentRepository.getDepartmentStats();
        Map<Long, DepartmentRepository.DepartmentStats> statsMap = statsList.stream()
                .collect(Collectors.toMap(DepartmentRepository.DepartmentStats::getDepartmentId, stat -> stat));

        Page<DepartmentResponse> mappedPage = departmentPage.map(department -> {
            DepartmentResponse response = departmentMapper.toResponse(department);
            DepartmentRepository.DepartmentStats stat = statsMap.get(department.getDepartmentId());
            if (stat != null) {
                response.setCompletedAppointments(stat.getCompletedAppointments());
                response.setDoctorCount(stat.getDoctorCount());
            }
            return response;
        });

        return new PageResponse<>(mappedPage);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(dept);
    }
}
