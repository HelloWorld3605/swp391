package com.example.kivicarebackend.entity;

import com.example.kivicarebackend.enums.doctors.DoctorRank;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {
    @Id
    @Column(name = "doctor_id")
    private Long doctorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "doctor_rank", nullable = false)
    private DoctorRank doctorRank = DoctorRank.INTERN;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "staff_id", insertable = false, updatable = false)
    private Staff staff;
}
