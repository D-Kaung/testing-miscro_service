package org.akh.microservice.shopservice.repository;

import org.akh.microservice.shopservice.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<Shop, Long> {
}
