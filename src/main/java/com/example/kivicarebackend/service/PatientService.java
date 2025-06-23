package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.PatientRequest;
import com.example.kivicarebackend.dto.request.PatientSearchRequest;
import com.example.kivicarebackend.dto.response.PatientResponse;
import com.example.kivicarebackend.util.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    List<PatientResponse> getAllPatients();
    PageResponse<PatientResponse> getPatientPage(Pageable pageable);
    PatientResponse getPatientById(Long id);
    PatientResponse createPatient(PatientRequest req);
    PatientResponse updatePatient(Long id, PatientRequest req);
    void deletePatient(Long id);
    PageResponse<PatientResponse> searchPatientPage(PatientSearchRequest req, Pageable pageable);
}
