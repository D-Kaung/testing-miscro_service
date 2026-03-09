package org.akh.microservice.orderserviceextend.mapper;

import org.akh.microservice.orderserviceextend.DTO.OrderCreateDTO;
import org.akh.microservice.orderserviceextend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

   OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

   Order orderDTOToOrder(OrderCreateDTO orderDTO);
   OrderCreateDTO orderToOrderDTO(Order order);
}
