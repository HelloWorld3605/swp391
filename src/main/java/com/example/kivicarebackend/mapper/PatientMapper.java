package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.PatientRequest;
import com.example.kivicarebackend.dto.response.PatientResponse;
import com.example.kivicarebackend.entity.Patient;
import com.example.kivicarebackend.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(source = "user.userId", target = "userId")
    PatientResponse toResponse(Patient entity);
    List<PatientResponse> toResponseList(List<Patient> entities);

    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "user", ignore = true)
    Patient toEntity(PatientRequest request);

    // For service use: set User reference after mapping
    default Patient toEntity(PatientRequest request, User user) {
        Patient patient = toEntity(request);
        patient.setUser(user);
        return patient;
    }
}
