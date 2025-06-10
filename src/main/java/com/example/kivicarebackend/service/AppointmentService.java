package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.AppointmentRequest;
import com.example.kivicarebackend.dto.response.AppointmentResponse;
import com.example.kivicarebackend.enums.appoinements.AppointmentStatus;
import com.example.kivicarebackend.util.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {
    AppointmentResponse create(AppointmentRequest req);
    AppointmentResponse update(Long id, AppointmentRequest req);
    AppointmentResponse getById(Long id);
    void delete(Long id);
    PageResponse<AppointmentResponse> getPage(Pageable pageable);

    // Thêm các hàm filter nếu cần:
    PageResponse<AppointmentResponse> getByDoctorId(Long doctorId, Pageable pageable);
    PageResponse<AppointmentResponse> getByPatientId(Long patientId, Pageable pageable);
    PageResponse<AppointmentResponse> getByStatus(String status, Pageable pageable);

    PageResponse<AppointmentResponse> getUpcoming(Pageable pageable);
    PageResponse<AppointmentResponse> getUpcomingByStatus(List<AppointmentStatus> statuses, Pageable pageable);

    PageResponse<AppointmentResponse> getPendingAppointments(Pageable pageable);

}
