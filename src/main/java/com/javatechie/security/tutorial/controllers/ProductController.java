package com.javatechie.security.tutorial.controllers;

import com.javatechie.security.tutorial.config.dto.AuthRequest;
import com.javatechie.security.tutorial.models.Product;
import com.javatechie.security.tutorial.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome: this endpoint has no authentication nor authorisation.";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<Product> getAllProducts() {
        return this.productService.getProductList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Product getProductById(@PathVariable("id") int id) {
        return this.productService.getProduct(id);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

    }
}
