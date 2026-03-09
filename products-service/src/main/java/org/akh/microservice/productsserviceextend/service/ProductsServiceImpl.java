package org.akh.microservice.productsserviceextend.service;

import org.akh.microservice.productsserviceextend.DTO.ProductsCreateDTO;
import org.akh.microservice.productsserviceextend.entity.Products;
import org.akh.microservice.productsserviceextend.mapper.ProductsMapper;
import org.akh.microservice.productsserviceextend.repostiory.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepo productRepo;
    private final ProductsMapper productsMapper;

    public ProductsServiceImpl(ProductRepo productRepo, ProductsMapper productsMapper) {
        this.productRepo = productRepo;
        this.productsMapper = productsMapper;
    }

    @Override
    public ProductsCreateDTO createProduct(ProductsCreateDTO productsCreateDTO) {
        Products products = productsMapper.toEntity(productsCreateDTO);
        products.setProductName(productsCreateDTO.getProductName());
        products.setPrice(productsCreateDTO.getPrice());
        productRepo.save(products);
        return productsMapper.toDto(products);
    }

    @Override
    public List<Products> getProductsList() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Products> getProductsById(Long id)throws RuntimeException {
        if (id != null) {
            return productRepo.findById(id);
        }else {
            throw new NullPointerException("PRODUCTS ID DOES NOT EXIST");
        }
    }

}
