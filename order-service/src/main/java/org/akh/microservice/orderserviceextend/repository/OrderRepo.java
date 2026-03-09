package org.akh.microservice.orderserviceextend.repository;

import org.akh.microservice.orderserviceextend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
