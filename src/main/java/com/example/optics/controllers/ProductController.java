package com.example.optics.controllers;


import com.example.optics.models.Product;
import com.example.optics.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс-контроллер для выполнения HTTP запросов админом
 */
@RestController
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("/delProduct")
    public void delProduct(Long id) {
        productService.delete(id);
    }

    @GetMapping("allProducts/{category}")
    public List<Product> allProductsByCategory(@PathVariable String category) {
        return productService.allProductsByCategory(category);
    }

    @GetMapping("product/{id}")
    public Product productById(@PathVariable Long id) {
        return productService.findProductById(id);
    }
}
