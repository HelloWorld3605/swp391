package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.DoctorRequest;
import com.example.kivicarebackend.dto.response.DoctorResponse;
import com.example.kivicarebackend.entity.Doctor;
import com.example.kivicarebackend.entity.Staff;
import com.example.kivicarebackend.enums.doctors.DoctorRank;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "doctorId", source = "staffId")
    @Mapping(target = "doctorRank", expression = "java(mapRankLevelToEnum(request.getRankLevel()))")
    Doctor toEntity(DoctorRequest request);

    @Mapping(target = "doctorId", source = "doctor.doctorId")
    @Mapping(target = "doctorRank", source = "doctor.doctorRank")
    @Mapping(target = "fullName", source = "staff.fullName")
    DoctorResponse toResponse(Doctor doctor, Staff staff);

    default DoctorRank mapRankLevelToEnum(int level) {
        return switch (level) {
            case 1 -> DoctorRank.INTERN;
            case 2 -> DoctorRank.RESIDENT;
            case 3 -> DoctorRank.ATTENDING;
            case 4 -> DoctorRank.SPECIALIST;
            case 5 -> DoctorRank.SENIOR_SPECIALIST;
            case 6 -> DoctorRank.CONSULTANT;
            case 7 -> DoctorRank.CHIEF_PHYSICIAN;
            default -> DoctorRank.INTERN;
        };
    }
}
