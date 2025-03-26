package com.product.product_query_service.controller;

import com.product.product_query_service.entity.Product;
import com.product.product_query_service.repository.ProductRepository;
import com.product.product_query_service.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryRepository {

    @Autowired
    ProductQueryService productQueryService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productQueryService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id){
        return productQueryService.getProductById(id);
    }
}
