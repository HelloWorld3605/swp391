package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.DoctorRequest;
import com.example.kivicarebackend.dto.response.DoctorResponse;
import com.example.kivicarebackend.entity.Doctor;
import com.example.kivicarebackend.entity.Staff;
import com.example.kivicarebackend.mapper.DoctorMapper;
import com.example.kivicarebackend.repository.DoctorRepository;
import com.example.kivicarebackend.repository.StaffRepository;
import com.example.kivicarebackend.service.DoctorService;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final StaffRepository staffRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorResponse createDoctor(DoctorRequest request) {
        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Doctor doctor = doctorMapper.toEntity(request);
        doctor = doctorRepository.save(doctor);
        return doctorMapper.toResponse(doctor, staff);
    }

    @Override
    public PageResponse<DoctorResponse> getAllDoctorsPaged(int page, int size) {
        Page<Doctor> doctorPage = doctorRepository.findAll(PageRequest.of(page, size));
        Page<DoctorResponse> mappedPage = doctorPage.map(doc -> {
            Staff staff = staffRepository.findById(doc.getDoctorId()).orElse(null);
            return doctorMapper.toResponse(doc, staff);
        });
        return new PageResponse<>(mappedPage);
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Staff staff = staffRepository.findById(doctor.getDoctorId()).orElse(null);
        return doctorMapper.toResponse(doctor, staff);
    }
    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setDoctorRank(doctorMapper.mapRankLevelToEnum(request.getRankLevel()));
        doctorRepository.save(doctor);

        Staff staff = staffRepository.findById(id).orElse(null);
        return doctorMapper.toResponse(doctor, staff);
    }

}
