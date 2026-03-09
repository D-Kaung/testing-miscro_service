package org.akh.microservice.orderserviceextend.service;

import org.akh.microservice.orderserviceextend.DTO.OrderCreateDTO;

public interface OrderService {

   OrderCreateDTO createOrder(OrderCreateDTO orderCreateDTO,Long id);
}
