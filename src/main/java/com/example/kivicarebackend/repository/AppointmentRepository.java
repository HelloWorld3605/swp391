package com.example.kivicarebackend.repository;

import com.example.kivicarebackend.entity.Appointment;
import com.example.kivicarebackend.enums.appoinements.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Đếm số appointment hoàn thành cho từng department
    @Query("""
        SELECT s.department.departmentId, COUNT(a)
        FROM Appointment a
        JOIN a.doctor s
        WHERE a.appointmentStatus = 'COMPLETED'
        GROUP BY s.department.departmentId
    """)
    List<Object[]> countCompletedAppointmentByDepartmentRaw();

    default Map<Long, Long> countCompletedAppointmentByDepartment() {
        Map<Long, Long> map = new HashMap<>();
        for (Object[] row : countCompletedAppointmentByDepartmentRaw()) {
            map.put((Long) row[0], (Long) row[1]);
        }
        return map;
    }
    Page<Appointment> findByDoctor_StaffId(Long doctorId, Pageable pageable);
    Page<Appointment> findByPatient_PatientId(Long patientId, Pageable pageable);
    Page<Appointment> findByAppointmentStatus(AppointmentStatus status, Pageable pageable);

    Page<Appointment> findByStartTimeAfterAndAppointmentStatusIn(
            LocalDateTime now,
            List<AppointmentStatus> statusList,
            Pageable pageable
    );

    // Nếu muốn lấy tất cả upcoming (bỏ status) thì chỉ cần:
    Page<Appointment> findByStartTimeAfter(LocalDateTime now, Pageable pageable);

}
