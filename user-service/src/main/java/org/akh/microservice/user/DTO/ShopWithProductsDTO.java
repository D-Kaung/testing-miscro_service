package org.akh.microservice.user.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.akh.microservice.user.feign.Products;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ShopWithProductsDTO {

    private Long id;
    private String shopName;
    private String shopAddress;
    private List<Products> productsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }
}
