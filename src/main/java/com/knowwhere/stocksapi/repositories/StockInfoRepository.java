package com.knowwhere.stocksapi.repositories;

import com.knowwhere.stocksapi.models.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockInfoRepository
        extends JpaRepository<StockInfo, Integer> {
    boolean existsByName(String name);

    Optional<StockInfo> findByName(String name);
}
