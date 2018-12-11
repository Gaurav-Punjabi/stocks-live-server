package com.knowwhere.stocksapi.repositories;

import com.knowwhere.stocksapi.models.StockCall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockCallRepository
        extends JpaRepository<StockCall, Integer> {
      List<StockCall> getAllByCallType(int typeId);

      List<StockCall> getAllByStockInfo(int id);
}
