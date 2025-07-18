package com.example.kivicarebackend.entity;

import com.example.kivicarebackend.enums.users.UserRole;
import com.example.kivicarebackend.enums.users.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "two_factor_enabled")
    private Boolean twoFactorEnabled;
}

