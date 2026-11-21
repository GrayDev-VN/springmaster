package com.springmaster.study.mapper;

import com.springmaster.study.dto.record.ProductResponse;
import com.springmaster.study.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {
    ProductResponse toResponse(Product product);
}
