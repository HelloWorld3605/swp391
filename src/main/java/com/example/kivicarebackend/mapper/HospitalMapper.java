package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.HospitalRequest;
import com.example.kivicarebackend.dto.response.HospitalResponse;
import com.example.kivicarebackend.entity.Hospital;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HospitalMapper {

    HospitalResponse toResponse(Hospital entity);

    List<HospitalResponse> toResponseList(List<Hospital> entities);

    @Mapping(target = "hospitalId", ignore = true)
    Hospital toEntity(HospitalRequest request);
}
