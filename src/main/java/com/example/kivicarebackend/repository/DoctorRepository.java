package com.example.kivicarebackend.repository;

import com.example.kivicarebackend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
