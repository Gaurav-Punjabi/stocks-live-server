package com.knowwhere.stocksapi.repositories;

import com.knowwhere.stocksapi.models.StockType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTypeRepository
        extends JpaRepository<StockType, Integer> {

}
