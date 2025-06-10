package com.example.kivicarebackend.specification;

import com.example.kivicarebackend.entity.Staff;
import org.springframework.data.jpa.domain.Specification;

public class StaffSpecification {

    public static Specification<Staff> hasUserId(Long userId) {
        return (root, query, cb) ->
                userId == null ? null : cb.equal(root.get("user").get("userId"), userId);
    }
    public static Specification<Staff> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) return null;
            String kw = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("fullName")), kw),
                    cb.like(cb.lower(root.get("user").get("email")), kw),
                    cb.like(cb.lower(root.get("user").get("phoneNumber")), kw)
            );
        };
    }

    public static Specification<Staff> hasFullName(String fullName) {
        return (root, query, cb) ->
                fullName == null ? null : cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%");
    }

    public static Specification<Staff> hasHospitalId(Long hospitalId) {
        return (root, query, cb) ->
                hospitalId == null ? null : cb.equal(root.get("hospital").get("hospitalId"), hospitalId);
    }

    public static Specification<Staff> hasDepartmentId(Long departmentId) {
        return (root, query, cb) ->
                departmentId == null ? null : cb.equal(root.get("department").get("departmentId"), departmentId);
    }

    public static Specification<Staff> hasRankLevel(Integer rankLevel) {
        return (root, query, cb) ->
                rankLevel == null ? null : cb.equal(root.get("rankLevel"), rankLevel);
    }
}
