package com.example.kivicarebackend.repository;

import com.example.kivicarebackend.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {}