package com.example.kivicarebackend.service;

import com.example.kivicarebackend.dto.request.ProductRequest;
import com.example.kivicarebackend.dto.request.ProductSearchRequest;
import com.example.kivicarebackend.dto.response.ProductResponse;
import com.example.kivicarebackend.util.PageResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
    ProductResponse getById(Long id);

    PageResponse<ProductResponse> getAll(Pageable pageable);
    PageResponse<ProductResponse> filter(ProductSearchRequest filter, Pageable pageable); // <-- filter động
}
