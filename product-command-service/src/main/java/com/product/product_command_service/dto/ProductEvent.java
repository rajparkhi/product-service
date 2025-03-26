package com.product.product_command_service.dto;

import com.product.product_command_service.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {

    private String eventType;

    private Product product;
}
