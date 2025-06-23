
package com.example.kivicarebackend.service.impl;
import com.example.kivicarebackend.dto.request.AppointmentRequest;
import com.example.kivicarebackend.dto.response.AppointmentResponse;
import com.example.kivicarebackend.entity.*;
import com.example.kivicarebackend.enums.appoinements.AppointmentStatus;
import com.example.kivicarebackend.mapper.AppointmentMapper;
import com.example.kivicarebackend.repository.*;
import com.example.kivicarebackend.service.AppointmentService;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final StaffRepository staffRepository;
    private final PatientRepository patientRepository;
    private final ServiceRepository serviceRepository;

    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentResponse create(AppointmentRequest req) {
        Staff doctor = staffRepository.findById(req.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(req.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        com.example.kivicarebackend.entity.Service service = serviceRepository.findById(req.getServiceId()) // Đúng repo
                .orElseThrow(() -> new RuntimeException("Service not found"));
        Staff coordinator = null;
        if (req.getSchedulingCoordinatorId() != null) {
            coordinator = staffRepository.findById(req.getSchedulingCoordinatorId())
                    .orElseThrow(() -> new RuntimeException("Coordinator not found"));
        }
        Appointment entity = appointmentMapper.toEntity(req, doctor, patient, service, coordinator);
        entity = appointmentRepository.save(entity);
        return appointmentMapper.toResponse(entity);
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest req) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Staff doctor = staffRepository.findById(req.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(req.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        com.example.kivicarebackend.entity.Service service = serviceRepository.findById(req.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));
        Staff coordinator = null;
        if (req.getSchedulingCoordinatorId() != null) {
            coordinator = staffRepository.findById(req.getSchedulingCoordinatorId())
                    .orElseThrow(() -> new RuntimeException("Coordinator not found"));
        }
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setService(service);
        appointment.setSchedulingCoordinator(coordinator);
        appointment.setStartTime(req.getStartTime());
        appointment.setDurationMinutes(req.getDurationMinutes());
        appointment.setAppointmentStatus(req.getAppointmentStatus());
        appointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public AppointmentResponse getById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public PageResponse<AppointmentResponse> getPage(Pageable pageable) {
        Page<Appointment> page = appointmentRepository.findAll(pageable);
        Page<AppointmentResponse> mappedPage = page.map(appointmentMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }



    @Override
    public PageResponse<AppointmentResponse> getByDoctorId(Long doctorId, Pageable pageable) {
        Page<Appointment> page = appointmentRepository.findByDoctor_StaffId(doctorId, pageable);
        Page<AppointmentResponse> mappedPage = page.map(appointmentMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public PageResponse<AppointmentResponse> getByPatientId(Long patientId, Pageable pageable) {
        Page<Appointment> page = appointmentRepository.findByPatient_PatientId(patientId, pageable);
        Page<AppointmentResponse> mappedPage = page.map(appointmentMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public PageResponse<AppointmentResponse> getByStatus(String status, Pageable pageable) {
        Page<Appointment> page = appointmentRepository.findByAppointmentStatus(
                AppointmentStatus.valueOf(status), pageable);
        Page<AppointmentResponse> mappedPage = page.map(appointmentMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public PageResponse<AppointmentResponse> getUpcoming(Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Page<Appointment> page = appointmentRepository.findByStartTimeAfter(now, pageable);
        Page<AppointmentResponse> mappedPage = page.map(appointmentMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public PageResponse<AppointmentResponse> getUpcomingByStatus(List<AppointmentStatus> statuses, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Page<Appointment> page = appointmentRepository.findByStartTimeAfterAndAppointmentStatusIn(now, statuses, pageable);
        Page<AppointmentResponse> mappedPage = page.map(appointmentMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }

    @Override
    public PageResponse<AppointmentResponse> getPendingAppointments(Pageable pageable) {
        Page<Appointment> page = appointmentRepository.findByAppointmentStatus(AppointmentStatus.PENDING, pageable);
        Page<AppointmentResponse> mappedPage = page.map(appointmentMapper::toResponse);
        return new PageResponse<>(mappedPage);
    }


}
