package com.example.optics.repository;

import com.example.optics.models.HistoryOfOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для истории покупок
 */
@Repository
public interface HistoryOrdersRepository extends JpaRepository<HistoryOfOrders, Integer> {

    List<HistoryOfOrders> findAllByUserId(Long id);

}
