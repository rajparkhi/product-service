package com.product.product_query_service.service;

import com.product.product_query_service.dto.ProductEvent;
import com.product.product_query_service.entity.Product;
import com.product.product_query_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository productRepository;


    public Product getProductById(long id){
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void processProductEvent(ProductEvent productEvent){
        Product product= productEvent.getProduct();

        if(productEvent.getEventType().equals("CreateProduct")){
            productRepository.save(product);
        }else if(productEvent.getEventType().equals("UpdateProduct")){
            Product existingProduct = productRepository.findById(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
        }
    }
}
