package com.example.kivicarebackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String videoUrl;
    private String bannerUrl;
    private String slogan;

    @NotNull
    private Long managerId;

    @NotNull
    private Long hospitalId;
}
