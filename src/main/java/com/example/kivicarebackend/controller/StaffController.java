package com.example.kivicarebackend.controller;

import com.example.kivicarebackend.dto.request.StaffRequest;
import com.example.kivicarebackend.dto.request.StaffSearchRequest;
import com.example.kivicarebackend.dto.response.StaffResponse;
import com.example.kivicarebackend.service.StaffService;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public StaffResponse create(@RequestBody StaffRequest request) {
        return staffService.createStaff(request);
    }

    @GetMapping("/paged")
    public PageResponse<StaffResponse> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return staffService.getAllStaffPaged(page, size);
    }

    @GetMapping("/{id}")
    public StaffResponse getById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }


    @PostMapping("/search")
    public PageResponse<StaffResponse> search(
            @RequestBody StaffSearchRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return staffService.search(request, page, size);
    }

    @PutMapping("/{id}")
    public StaffResponse update(@PathVariable Long id, @RequestBody StaffRequest request) {
        return staffService.updateStaff(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }


}
