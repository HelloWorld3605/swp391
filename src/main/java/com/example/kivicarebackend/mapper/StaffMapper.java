package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.StaffRequest;
import com.example.kivicarebackend.dto.response.StaffResponse;
import com.example.kivicarebackend.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    // Map từ request -> entity (Staff)
    @Mapping(target = "staffId", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "manager", source = "managerId", qualifiedByName = "mapManager")
    @Mapping(target = "department", source = "departmentId", qualifiedByName = "mapDepartment")
    @Mapping(target = "hospital", source = "hospitalId", qualifiedByName = "mapHospital")
    Staff toEntity(StaffRequest request);

    // Map từ entity -> response (StaffResponse)
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "staffId", source = "staffId")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "staffRole", source = "staffRole")
    @Mapping(target = "staffType", source = "staffType")
    @Mapping(target = "rankLevel", source = "rankLevel")
    @Mapping(target = "hireDate", source = "hireDate")
    @Mapping(target = "department", source = "department")
    @Mapping(target = "hospital", source = "hospital")
    StaffResponse toResponse(Staff staff);

    // Các hàm map phụ (for Request -> Entity)
    @Named("mapUser")
    static User mapUser(Long id) {
        if (id == null) return null;
        User user = new User();
        user.setUserId(id);
        return user;
    }

    @Named("mapManager")
    static Staff mapManager(Long id) {
        if (id == null) return null;
        Staff manager = new Staff();
        manager.setStaffId(id);
        return manager;
    }

    @Named("mapDepartment")
    static Department mapDepartment(Long id) {
        if (id == null) return null;
        Department d = new Department();
        d.setDepartmentId(id);
        return d;
    }

    @Named("mapHospital")
    static Hospital mapHospital(Long id) {
        if (id == null) return null;
        Hospital h = new Hospital();
        h.setHospitalId(id);
        return h;
    }
}

