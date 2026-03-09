package org.akh.microservice.orderserviceextend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal qty;
    private String productName;
    private BigDecimal price;

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Order(Long id, String productName, BigDecimal price,BigDecimal qty) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.qty = qty;
    }
    public Order() {}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", qty=" + qty +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
