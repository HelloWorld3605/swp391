package com.example.kivicarebackend.dto.request;

import com.example.kivicarebackend.enums.patients.BloodType;
import com.example.kivicarebackend.enums.patients.Gender;
import com.example.kivicarebackend.enums.patients.Relationship;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class PatientRequest {
    @NotNull
    private Long userId;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;
    @NotBlank
    private String fullName;
    private String avatarUrl;
    @NotNull
    private Relationship relationship;
    private String address;
    @NotNull
    private Gender gender;
    @NotNull
    private LocalDate birthdate;
    private BloodType bloodType;
}
