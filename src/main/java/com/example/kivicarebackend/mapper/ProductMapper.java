package com.example.kivicarebackend.mapper;

import com.example.kivicarebackend.dto.request.ProductRequest;
import com.example.kivicarebackend.dto.response.ProductResponse;
import com.example.kivicarebackend.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest request);

    ProductResponse toResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromRequest(ProductRequest request, @MappingTarget Product product);
}
