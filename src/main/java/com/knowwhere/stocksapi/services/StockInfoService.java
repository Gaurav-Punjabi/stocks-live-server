package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.exceptions.FieldNotFoundException;
import com.knowwhere.stocksapi.models.StockInfo;
import com.knowwhere.stocksapi.models.StockType;
import com.knowwhere.stocksapi.repositories.StockInfoRepository;
import jdk.internal.org.objectweb.asm.tree.FieldNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockInfoService {

    @Autowired
    private StockInfoRepository stockInfoRepository;

    @Autowired
    private StockTypeService stockTypeService;

    public StockInfo add(String name,
                    int stockTypeId) throws FieldNotFoundException {
        Optional<StockType> stockTypeOptional = stockTypeService.getStockTypeById(stockTypeId);
        if(!stockTypeOptional.isPresent())
            throw new FieldNotFoundException("No Stock Type with id : " + stockTypeId + " was found");
        StockType stockType = stockTypeOptional.get();
        return this.stockInfoRepository.save(new StockInfo(name, stockType));
    }

    public Optional<StockInfo> getById(final int id) {
        return this.stockInfoRepository.findById(id);
    }

    public Optional<StockInfo> getByName(final String name) {
        return this.stockInfoRepository.findByName(name);
    }

    public boolean exists(String name) {
        return this.stockInfoRepository.existsByName(name);
    }
}
