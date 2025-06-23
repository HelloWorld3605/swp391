package com.example.kivicarebackend.dto.response;
import com.example.kivicarebackend.enums.staffs.StaffRole;
import com.example.kivicarebackend.enums.staffs.StaffType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffResponse {
    private Long userId;
    private Long staffId;
    private String fullName;
    private StaffRole staffRole;
    private StaffType staffType;
    private int rankLevel;
    private LocalDate hireDate;
    private DepartmentResponse department;
    private HospitalResponse hospital;
}
