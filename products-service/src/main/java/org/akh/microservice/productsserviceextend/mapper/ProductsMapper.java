package org.akh.microservice.productsserviceextend.mapper;

import org.akh.microservice.productsserviceextend.DTO.ProductsCreateDTO;
import org.akh.microservice.productsserviceextend.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

  ProductsMapper INSTANCE = Mappers.getMapper(ProductsMapper.class);

  Products toEntity(ProductsCreateDTO dto);
  ProductsCreateDTO toDto(Products entity);

}
