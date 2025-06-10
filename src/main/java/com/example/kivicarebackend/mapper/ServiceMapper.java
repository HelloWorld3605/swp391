package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.ServiceRequest;
import com.example.kivicarebackend.dto.response.ServiceResponse;
import com.example.kivicarebackend.entity.Department;
import com.example.kivicarebackend.entity.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ServiceFeatureMapper.class})
public interface ServiceMapper {
    @Mapping(source = "department.departmentId", target = "departmentId")
    @Mapping(source = "features", target = "features")
    ServiceResponse toResponse(Service entity);
    List<ServiceResponse> toResponseList(List<Service> entities);

    @Mapping(target = "serviceId", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "features", ignore = true)
    Service toEntity(ServiceRequest request);

    // Inject department nếu cần
    default Service toEntity(ServiceRequest request, Department department) {
        Service service = toEntity(request);
        service.setDepartment(department);
        return service;
    }
}
