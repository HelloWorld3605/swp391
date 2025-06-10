package com.example.kivicarebackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// ServiceFeatureRequest.java
@Data
public class ServiceFeatureRequest {
    @NotNull
    private Long serviceId;
    @NotBlank
    private String name;
    private String description;
}

