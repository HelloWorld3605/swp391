package com.example.kivicarebackend.dto.request;

import com.example.kivicarebackend.enums.patients.BloodType;
import com.example.kivicarebackend.enums.patients.Gender;
import com.example.kivicarebackend.enums.patients.Relationship;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientSearchRequest {
    private Long userId;
    private Long patientId;
    private String keyword;
    private Gender gender;
    private Relationship relationship;
    private BloodType bloodType;
    private LocalDate birthdateFrom;
    private LocalDate birthdateTo;
    private String address;
}

