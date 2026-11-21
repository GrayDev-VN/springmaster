package com.springmaster.study.services;

import com.springmaster.study.dto.record.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponse> getAllProducts(String name, String category, Pageable pageable);
}
