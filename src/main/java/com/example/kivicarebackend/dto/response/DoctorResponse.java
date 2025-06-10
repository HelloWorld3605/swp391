package com.example.kivicarebackend.dto.response;

import com.example.kivicarebackend.enums.doctors.DoctorRank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponse {
    private Long doctorId;
    private DoctorRank doctorRank;
    private String fullName;
}
