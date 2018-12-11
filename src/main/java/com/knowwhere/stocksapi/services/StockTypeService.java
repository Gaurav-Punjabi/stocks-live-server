package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.models.StockType;
import com.knowwhere.stocksapi.repositories.StockTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockTypeService {
    @Autowired
    StockTypeRepository stockTypeRepository;

    public Optional<StockType> getStockTypeById(final int id) {
        return this.stockTypeRepository.findById(id);
    }
}
