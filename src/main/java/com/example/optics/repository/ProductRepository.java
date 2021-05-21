package com.example.optics.repository;


import com.example.optics.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для товаров
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByBrandName(String brandName);

    List<Product> findAllByCategory(String category);

    List<Product> findAllByCategory(String category, Sort sort);
}
