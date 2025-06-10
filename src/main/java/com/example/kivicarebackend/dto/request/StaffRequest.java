package com.example.kivicarebackend.dto.request;

import com.example.kivicarebackend.enums.staffs.StaffRole;
import com.example.kivicarebackend.enums.staffs.StaffType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StaffRequest {
    private Long userId;
    private StaffRole staffRole;
    private Long managerId;
    private Long departmentId;
    private String fullName;
    private String avatarUrl;
    private LocalDate hireDate;
    private int rankLevel;
    private StaffType staffType;
    private Long hospitalId;
}
