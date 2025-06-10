package com.example.kivicarebackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffSearchRequest {
    private Long userId;
    private String keyword;
    private String fullName;
    private Long hospitalId;
    private Long departmentId;
    private Integer rankLevel;
}
