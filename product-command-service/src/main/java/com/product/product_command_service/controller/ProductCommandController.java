package com.product.product_command_service.controller;

import com.product.product_command_service.dto.ProductEvent;
import com.product.product_command_service.entity.Product;
import com.product.product_command_service.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private ProductCommandService productCommandService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent){
        return productCommandService.createProduct(productEvent);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody ProductEvent productEvent){
        return productCommandService.updateProduct(id, productEvent);
    }
}
