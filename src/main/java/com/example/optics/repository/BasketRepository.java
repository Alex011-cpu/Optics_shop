package com.example.optics.repository;

import com.example.optics.models.Basket;
import com.example.optics.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для корзины
 */
@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByProduct_Id(Long id);

    @Query("SELECT SUM (m.count) from Basket m")
    Optional<Integer> allCount();

    @Query("SELECT SUM (m.sum) from Basket m")
    Optional<Integer> sum();

    @Override
    List<Basket> findAll(Sort sort);

    @Override
    void deleteAll();
}