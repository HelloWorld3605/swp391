package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.AppointmentRequest;
import com.example.kivicarebackend.dto.response.AppointmentResponse;
import com.example.kivicarebackend.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(source = "doctor.staffId", target = "doctorId")
    @Mapping(source = "doctor.fullName", target = "doctorName")
    @Mapping(source = "patient.patientId", target = "patientId")
    @Mapping(source = "patient.fullName", target = "patientName")
    @Mapping(source = "service.serviceId", target = "serviceId")
    @Mapping(source = "service.features", target = "serviceFeatures")
    @Mapping(source = "schedulingCoordinator.staffId", target = "schedulingCoordinatorId")
    @Mapping(source = "schedulingCoordinator.fullName", target = "schedulingCoordinatorName")
    AppointmentResponse toResponse(Appointment entity);

    List<AppointmentResponse> toResponseList(List<Appointment> entities);

    @Mapping(target = "appointmentId", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "schedulingCoordinator", ignore = true)
    Appointment toEntity(AppointmentRequest req);

    default Appointment toEntity(AppointmentRequest req, Staff doctor, Patient patient, Service service, Staff coordinator) {
        Appointment appointment = toEntity(req);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setService(service);
        appointment.setSchedulingCoordinator(coordinator);
        return appointment;
    }
}

