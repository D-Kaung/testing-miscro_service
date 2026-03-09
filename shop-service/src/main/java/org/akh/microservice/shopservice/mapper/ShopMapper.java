package org.akh.microservice.shopservice.mapper;

import org.akh.microservice.shopservice.dto.ShopCreateDTO;
import org.akh.microservice.shopservice.dto.ShopListDTO;
import org.akh.microservice.shopservice.entity.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    Shop toEntity(ShopCreateDTO shopCreateDTO);
    ShopCreateDTO toDTO(Shop shop);

}
