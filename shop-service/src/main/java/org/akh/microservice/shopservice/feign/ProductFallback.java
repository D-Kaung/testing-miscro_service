package org.akh.microservice.shopservice.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductFallback implements ProductsInterface{

    private static final Logger log = LoggerFactory.getLogger(ProductFallback.class);

    @Override
    public List<Products> getProductsList() {
        log.error("RETRYING WITH RETRY PATTERN TO CALL PRODUCTS-SERVICE!!");
        return Collections.emptyList();
    }

    @Override
    public Products getProductsById(Long id) {
        log.error("RETRYING WITH RETRY PATTERN TO CALL PRODUCTS-SERVICE!!!");
        return null;
    }
}
