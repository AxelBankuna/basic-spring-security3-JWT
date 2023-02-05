package com.javatechie.security.tutorial.services;

import com.javatechie.security.tutorial.models.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList =null;

    @PostConstruct
    public void loadProductsFromDB() {
        this.productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                .id(i)
                .name("product " + i)
                .quantity(new Random().nextInt(10))
                .price(new Random().nextInt(5000))
                .build()
                ).collect(Collectors.toList());
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public Product getProduct(int id) {
        return this.productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product " + id + " not found."));
    }
}
