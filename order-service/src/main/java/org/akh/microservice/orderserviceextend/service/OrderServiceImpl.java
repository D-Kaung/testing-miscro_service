package org.akh.microservice.orderserviceextend.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.akh.microservice.orderserviceextend.DTO.OrderCreateDTO;
import org.akh.microservice.orderserviceextend.entity.Order;
import org.akh.microservice.orderserviceextend.exception.ProductIdDoesNotExistException;
import org.akh.microservice.orderserviceextend.feign.Products;
import org.akh.microservice.orderserviceextend.feign.ProductsInterface;
import org.akh.microservice.orderserviceextend.mapper.OrderMapper;
import org.akh.microservice.orderserviceextend.repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final ProductsInterface productsInterface;
    private final Logger log =  Logger.getLogger(OrderServiceImpl.class.getName());

    public  OrderServiceImpl(OrderRepo orderRepo, OrderMapper orderMapper,ProductsInterface productsInterface) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.productsInterface = productsInterface;
    }

    @Override
    @Retry(name = "products-service")
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackOrder")
    public OrderCreateDTO createOrder(OrderCreateDTO orderCreateDTO,Long id)throws ProductIdDoesNotExistException {
        Order order = orderMapper.orderDTOToOrder(orderCreateDTO);
        Products products = productsInterface.getProductsById(id);
        if (products == null){
            throw new ProductIdDoesNotExistException("THIS PRODUCTS ID IS NOT FOUND");
        }
        order.setQty(order.getQty());
        order.setPrice(products.getPrice());
        order.setProductName(products.getProductName());
        orderRepo.save(order);
        return orderMapper.orderToOrderDTO(order);
    }

    public OrderCreateDTO fallbackOrder(OrderCreateDTO orderCreateDTO,Long id,Throwable throwable) {
         orderCreateDTO.setQty(orderCreateDTO.getQty());
         orderCreateDTO.setProductName("PRODUCT-SERVICE IS DOWN!!!");
         orderCreateDTO.setPrice(null);
         return orderCreateDTO;
    }
}
