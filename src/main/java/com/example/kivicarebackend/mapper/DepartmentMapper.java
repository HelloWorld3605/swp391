package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.DepartmentRequest;
import com.example.kivicarebackend.dto.response.DepartmentResponse;
import com.example.kivicarebackend.entity.Department;
import com.example.kivicarebackend.entity.Hospital;
import com.example.kivicarebackend.entity.Staff;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    @Mapping(source = "manager.staffId", target = "managerId")
    DepartmentResponse toResponse(Department entity);

    List<DepartmentResponse> toResponseList(List<Department> entities);

    @Mapping(target = "departmentId", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "hospital", ignore = true)
    Department toEntity(DepartmentRequest request);

    default Department toEntity(DepartmentRequest request, Staff manager, Hospital hospital) {
        Department dept = toEntity(request);
        dept.setManager(manager);
        dept.setHospital(hospital);
        return dept;
    }
}
