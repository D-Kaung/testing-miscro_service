package org.akh.microservice.orderserviceextend.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.akh.microservice.orderserviceextend.feign.Products;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {

    private BigDecimal qty;
    private String productName;
    private BigDecimal price;

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
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
}
