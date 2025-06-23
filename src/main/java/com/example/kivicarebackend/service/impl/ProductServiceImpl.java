package com.example.kivicarebackend.service.impl;

import com.example.kivicarebackend.dto.request.ProductRequest;
import com.example.kivicarebackend.dto.request.ProductSearchRequest;
import com.example.kivicarebackend.dto.response.ProductResponse;
import com.example.kivicarebackend.entity.Product;
import com.example.kivicarebackend.mapper.ProductMapper;
import com.example.kivicarebackend.repository.ProductRepository;
import com.example.kivicarebackend.service.ProductService;
import com.example.kivicarebackend.specification.ProductSpecification;
import com.example.kivicarebackend.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy sản phẩm"));

        // Áp dụng chỉ update các field không null trong request
        productMapper.updateProductFromRequest(request, product);

        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) throw new NoSuchElementException("Không tìm thấy sản phẩm");
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy sản phẩm"));
        return productMapper.toResponse(product);
    }

    @Override
    public PageResponse<ProductResponse> getAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(pageable);
        Page<ProductResponse> mapped = page.map(productMapper::toResponse);
        return new PageResponse<>(mapped);
    }

    @Override
    public PageResponse<ProductResponse> filter(ProductSearchRequest filter, Pageable pageable) {
        Page<Product> page = productRepository.findAll(ProductSpecification.filter(filter), pageable);
        Page<ProductResponse> mapped = page.map(productMapper::toResponse);
        return new PageResponse<>(mapped);
    }
}
