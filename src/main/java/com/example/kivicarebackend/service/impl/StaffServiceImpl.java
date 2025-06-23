package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.StaffRequest;
import com.example.kivicarebackend.dto.request.StaffSearchRequest;
import com.example.kivicarebackend.dto.response.StaffResponse;
import com.example.kivicarebackend.entity.Department;
import com.example.kivicarebackend.entity.Hospital;
import com.example.kivicarebackend.entity.Staff;
import com.example.kivicarebackend.entity.User;
import com.example.kivicarebackend.mapper.StaffMapper;
import com.example.kivicarebackend.repository.DepartmentRepository;
import com.example.kivicarebackend.repository.HospitalRepository;
import com.example.kivicarebackend.repository.StaffRepository;
import com.example.kivicarebackend.repository.UserRepository;
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
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    @Override
    public StaffResponse createStaff(StaffRequest request) {
        // Lấy các entity liên kết từ DB (nếu có)
        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
        }

        Hospital hospital = null;
        if (request.getHospitalId() != null) {
            hospital = hospitalRepository.findById(request.getHospitalId())
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));
        }

        Staff manager = null;
        if (request.getManagerId() != null) {
            manager = staffRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
        }

        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        // MapStruct chỉ map các trường đơn giản
        Staff staff = staffMapper.toEntity(request);

        // Gán các entity đã fetch từ DB
        staff.setDepartment(department);
        staff.setHospital(hospital);
        staff.setManager(manager);
        staff.setUser(user);

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
    public PageResponse<StaffResponse> search(StaffSearchRequest request, int page, int size) {
        Specification<Staff> spec = StaffSpecification.filter(request);
        Page<Staff> staffPage = staffRepository.findAll(spec, PageRequest.of(page, size));
        Page<StaffResponse> mappedPage = staffPage.map(staffMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public StaffResponse updateStaff(Long id, StaffRequest request) {
        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
        }

        Hospital hospital = null;
        if (request.getHospitalId() != null) {
            hospital = hospitalRepository.findById(request.getHospitalId())
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));
        }

        Staff manager = null;
        if (request.getManagerId() != null) {
            manager = staffRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
        }

        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        // MapStruct chỉ map trường đơn giản
        Staff updated = staffMapper.toEntity(request);
        updated.setStaffId(id);

        // Gán entity đã fetch từ DB
        updated.setDepartment(department);
        updated.setHospital(hospital);
        updated.setManager(manager);
        updated.setUser(user);

        staffRepository.save(updated);
        return staffMapper.toResponse(updated);
    }

    @Override
    public void deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        staffRepository.delete(staff);
    }


}
