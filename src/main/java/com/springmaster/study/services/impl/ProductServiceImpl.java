package com.springmaster.study.services.impl;

import com.springmaster.study.dto.record.ProductResponse;
import com.springmaster.study.entity.Product;
import com.springmaster.study.mapper.ProductMapper;
import com.springmaster.study.repository.ProductRepository;
import com.springmaster.study.repository.specifications.ProductSpecification;
import com.springmaster.study.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponse> getAllProducts(String name, String category, Pageable pageable) {
//        Page<Product> products = productRepository.findAll(pageable);
        // Custom query
//        Page<Product> products = productRepository.findActiveByCategory(category, pageable);

        //JpaSpecification
        Page<Product> products = productRepository.findAll(ProductSpecification.fillerProducts(name, BigDecimal.valueOf(10000), category), pageable);
        return products.map(productMapper::toResponse);
    }
}
