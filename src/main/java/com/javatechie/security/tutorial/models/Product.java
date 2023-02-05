package com.javatechie.security.tutorial.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer quantity;
}
