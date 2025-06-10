package com.example.kivicarebackend.dto.response;

import com.example.kivicarebackend.enums.staffs.StaffRole;
import com.example.kivicarebackend.enums.staffs.StaffType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StaffResponse {
    private Long staffId;
    private String fullName;
    private StaffRole staffRole;
    private StaffType staffType;
    private int rankLevel;
    private LocalDate hireDate;
}
