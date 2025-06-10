package com.example.kivicarebackend.controller;

import com.example.kivicarebackend.dto.request.AppointmentRequest;
import com.example.kivicarebackend.dto.response.AppointmentResponse;
import com.example.kivicarebackend.enums.appoinements.AppointmentStatus;
import com.example.kivicarebackend.service.AppointmentService;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.create(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> update(@PathVariable Long id, @RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<AppointmentResponse>> getPage(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getPage(pageable));
    }
    @GetMapping("/by-doctor/{doctorId}")
    public ResponseEntity<PageResponse<AppointmentResponse>> getByDoctor(
            @PathVariable Long doctorId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getByDoctorId(doctorId, pageable));
    }
    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<PageResponse<AppointmentResponse>> getByPatient(
            @PathVariable Long patientId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getByPatientId(patientId, pageable));
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<PageResponse<AppointmentResponse>> getByStatus(
            @PathVariable String status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getByStatus(status, pageable));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<PageResponse<AppointmentResponse>> getUpcoming(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getUpcoming(pageable));
    }

    @GetMapping("/upcoming-by-status")
    public ResponseEntity<PageResponse<AppointmentResponse>> getUpcomingByStatus(
            @RequestParam List<AppointmentStatus> statuses,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getUpcomingByStatus(statuses, pageable));
    }

    @GetMapping("/pending")
    public ResponseEntity<PageResponse<AppointmentResponse>> getPendingAppointments(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.getPendingAppointments(pageable));
    }

}
