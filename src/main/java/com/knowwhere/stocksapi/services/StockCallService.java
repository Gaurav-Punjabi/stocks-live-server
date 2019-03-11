package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.controllers.CommodityController;
import com.knowwhere.stocksapi.exceptions.FieldNotFoundException;
import com.knowwhere.stocksapi.models.CallType;
import com.knowwhere.stocksapi.models.StockCall;
import com.knowwhere.stocksapi.models.StockInfo;
import com.knowwhere.stocksapi.repositories.StockCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockCallService {

      @Autowired
      private StockCallRepository stockCallRepository;

      @Autowired
      StockInfoService stockInfoService;

      @Autowired
      CallTypeService callTypeService;

      public List<StockCall> getAllStockCalls() {
            return this.stockCallRepository.findAll();
      }

      public StockCall setCompleted(StockCall stockCall) {
            stockCall.setCompleted(true);
            return this.stockCallRepository.save(stockCall);
      }

      public List<StockCall> getAllByStockInfo(int id) {
            return this.stockCallRepository.getAllByStockInfo(id);
      }

      public StockCall add(final String name,
                           final String type,
                           final double currentPrice,
                           final double stopLoss,
                           final double target1,
                           final double target2,
                           final double target3) throws FieldNotFoundException {
            Optional<StockInfo> optionalStockInfo = this.stockInfoService.getByName(name);
            StockInfo stockInfo = null;
            if (!optionalStockInfo.isPresent()) {
                  stockInfo = this.stockInfoService.add(name, 1);
            } else {
                  stockInfo = optionalStockInfo.get();
            }
            Optional<CallType> optionalCallType = this.callTypeService.getByName(type);
            if (optionalCallType.isPresent()) {
                  CallType callType = optionalCallType.get();
                  System.out.println("callType = " + callType);
                  StockCall stockCall = new StockCall(stockInfo, callType, currentPrice, stopLoss, target1, target2, target3);
                  System.out.println("stockCall = " + stockCall);
                  stockCall.setCreatedAt(new Date());
                  System.out.println("stockCall = " + stockCall);
                  return this.stockCallRepository.save(stockCall);
            }
            return null;
      }

}
