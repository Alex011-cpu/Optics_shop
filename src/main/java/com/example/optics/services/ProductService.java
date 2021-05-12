package com.example.optics.services;


import com.example.optics.models.Product;
import com.example.optics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public boolean addProduct(Product product) {
        if (product.getBrandName().equals(findProductByBrandName(product.getBrandName()).getBrandName())) return false;
        productRepository.save(product);
        return true;
    }

    public Product findProductByBrandName(String brandName) {
        return productRepository.findByBrandName(brandName).orElse(null);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public boolean delete(Long id) {
        Product productFromDb = findProductById(id);
        if (findProductByBrandName(productFromDb.getBrandName()) == null) return false;
        productRepository.delete(productFromDb);
        return true;
    }

    public List<Product> allProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    public List<Product> allProductsByCategoryAndSortedByPriceDesc(String category) {
        return productRepository.findAllByCategory(category, Sort.by(Sort.Direction.DESC,"price"));
    }

    public List<Product> allProductsByCategoryAndSortedByPriceAsc(String category) {
        return productRepository.findAllByCategory(category, Sort.by(Sort.Direction.ASC,"price"));
    }

    public List<Product> allProductsByCategoryAndSortedByBrandDesc(String category) {
        return productRepository.findAllByCategory(category, Sort.by(Sort.Direction.DESC,"brandName"));
    }

    public List<Product> allProductsByCategoryAndSortedByBrandAsc(String category) {
        return productRepository.findAllByCategory(category, Sort.by(Sort.Direction.ASC,"brandName"));
    }
}















