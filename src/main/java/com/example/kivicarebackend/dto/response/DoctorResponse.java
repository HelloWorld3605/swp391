package com.example.kivicarebackend.dto.response;

import com.example.kivicarebackend.enums.doctors.DoctorRank;
import lombok.Data;
@Data
public class DoctorResponse {
    private Long doctorId;
    private DoctorRank doctorRank;
    private String fullName;
}
