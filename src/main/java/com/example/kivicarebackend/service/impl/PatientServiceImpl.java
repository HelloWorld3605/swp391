package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.PatientRequest;
import com.example.kivicarebackend.dto.request.PatientSearchRequest;
import com.example.kivicarebackend.dto.response.PatientResponse;
import com.example.kivicarebackend.entity.Patient;
import com.example.kivicarebackend.entity.User;
import com.example.kivicarebackend.mapper.PatientMapper;
import com.example.kivicarebackend.repository.PatientRepository;
import com.example.kivicarebackend.repository.UserRepository;
import com.example.kivicarebackend.service.email.EmailService;
import com.example.kivicarebackend.service.PatientService;
import com.example.kivicarebackend.specification.PatientSpecification;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;

//    private final EmailService emailService;
//
//    private String generateOtp() {
//        int otp = (int)(Math.random() * 900000) + 100000;
//        return String.valueOf(otp);
//    }


    @Override
    public List<PatientResponse> getAllPatients() {
        return patientMapper.toResponseList(patientRepository.findAll());
    }

    @Override
    public PageResponse<PatientResponse> getPatientPage(Pageable pageable) {
        Page<Patient> page = patientRepository.findAll(pageable);
        Page<PatientResponse> mappedPage = page.map(patientMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toResponse(patient);
    }

    @Override
    @Transactional
    public PatientResponse createPatient(PatientRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Patient patient = patientMapper.toEntity(req, user);
        patient = patientRepository.save(patient);
        return patientMapper.toResponse(patient);
    }


    @Override
    @Transactional
    public PatientResponse updatePatient(Long id, PatientRequest req) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        patient.setUser(user);
        patient.setPhoneNumber(req.getPhoneNumber());
        patient.setEmail(req.getEmail());
        patient.setFullName(req.getFullName());
        patient.setAvatarUrl(req.getAvatarUrl());
        patient.setRelationship(req.getRelationship());
        patient.setAddress(req.getAddress());
        patient.setGender(req.getGender());
        patient.setBirthdate(req.getBirthdate());
        patient.setBloodType(req.getBloodType());
        patient = patientRepository.save(patient);
        return patientMapper.toResponse(patient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) throw new RuntimeException("Patient not found");
        patientRepository.deleteById(id);
    }

    @Override
    public PageResponse<PatientResponse> searchPatientPage(PatientSearchRequest req, Pageable pageable) {
        Specification<Patient> spec = PatientSpecification.filter(req);
        Page<Patient> page = patientRepository.findAll(spec, pageable);
        return new PageResponse<>(page.map(patientMapper::toResponse));
    }
}
