package org.akh.microservice.user.feign;

import io.github.resilience4j.retry.annotation.Retry;
import org.akh.microservice.user.DTO.OrderCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order-service")
public interface OrderInterface {

    @PostMapping("/order/create/{id}")
    @Retry(name = "order-service")
    OrderCreateDTO createOrder(@RequestBody OrderCreateDTO orderCreateDTO,
                               @PathVariable Long id);
}
