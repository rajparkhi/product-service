package com.product.product_command_service.service;

import com.product.product_command_service.dto.ProductEvent;
import com.product.product_command_service.entity.Product;
import com.product.product_command_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Product createProduct(ProductEvent productEvent){
        Product productDO = repository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent("CreateProduct", productDO);
        kafkaTemplate.send("product-event-topic", event);

        return productDO;
    }

    @KafkaListener(topics = "product-event-topic", groupId = "product-event-group")
    public Product updateProduct(long id, ProductEvent productEvent){
        Optional<Product> existingProduct = repository.findById(id);
        if(existingProduct.isPresent()){
            Product newProduct = productEvent.getProduct();
            existingProduct.get().setName(newProduct.getName());
            existingProduct.get().setDescription(newProduct.getDescription());
            existingProduct.get().setPrice(newProduct.getPrice());

            Product productDO = repository.save(existingProduct.get());
            ProductEvent event = new ProductEvent("UpdateProduct", productDO);
            kafkaTemplate.send("product-event-topic", event);
            return productDO;
        }
        return null;
    }
}
