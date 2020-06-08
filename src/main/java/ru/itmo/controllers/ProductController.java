package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.models.user.Product;
import ru.itmo.services.ProductService;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = { "", "/" })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
}