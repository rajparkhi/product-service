package com.product.product_command_service.service;

import com.product.product_command_service.entity.Product;
import com.product.product_command_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCommandService {

    @Autowired
    private ProductRepository repository;

    public Product createProduct(Product product){
        return repository.save(product);
    }

    public Product updateProduct(long id, Product product){
        Product existingProduct = repository.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        return createProduct(existingProduct);
    }
}
