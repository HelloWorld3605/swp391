package com.example.kivicarebackend.controller;

import com.example.kivicarebackend.dto.request.DoctorRequest;
import com.example.kivicarebackend.dto.response.DoctorResponse;
import com.example.kivicarebackend.service.DoctorService;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public DoctorResponse create(@RequestBody DoctorRequest request) {
        return doctorService.createDoctor(request);
    }

    @GetMapping("/paged")
    public PageResponse<DoctorResponse> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return doctorService.getAllDoctorsPaged(page, size);
    }

    @GetMapping("/{id}")
    public DoctorResponse getById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @PutMapping("/{id}")
    public DoctorResponse update(@PathVariable Long id, @RequestBody DoctorRequest request) {
        return doctorService.updateDoctor(id, request);
    }
}
