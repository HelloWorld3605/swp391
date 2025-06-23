package com.example.kivicarebackend.repository;

import com.example.kivicarebackend.entity.Appointment;
import com.example.kivicarebackend.enums.appoinements.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findByDoctor_StaffId(Long doctorId, Pageable pageable);
    Page<Appointment> findByPatient_PatientId(Long patientId, Pageable pageable);
    Page<Appointment> findByAppointmentStatus(AppointmentStatus status, Pageable pageable);

    Page<Appointment> findByStartTimeAfterAndAppointmentStatusIn(
            LocalDateTime now,
            List<AppointmentStatus> statusList,
            Pageable pageable
    );

    Page<Appointment> findByStartTimeAfter(LocalDateTime now, Pageable pageable);

}
