package com.example.kivicarebackend.repository;

import com.example.kivicarebackend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    interface DepartmentStats {
        Long getDepartmentId();
        Long getCompletedAppointments();
        Long getDoctorCount();
    }

    @Query("""
        SELECT 
            d.departmentId AS departmentId,
            COUNT(DISTINCT CASE WHEN a.appointmentStatus = 'COMPLETED' THEN a.appointmentId END) AS completedAppointments,
            COUNT(DISTINCT s.staffId) AS doctorCount
        FROM Department d
        LEFT JOIN Staff s ON s.department = d AND s.staffRole = 'DOCTOR'
        LEFT JOIN Appointment a ON a.doctor = s AND a.appointmentStatus = 'COMPLETED'
        GROUP BY d.departmentId
    """)
    List<DepartmentStats> getDepartmentStats();
}
