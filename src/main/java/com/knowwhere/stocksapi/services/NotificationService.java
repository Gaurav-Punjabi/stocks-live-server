package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.controllers.CommodityController;
import com.knowwhere.stocksapi.models.Commodity;
import com.knowwhere.stocksapi.models.Notification;
import com.knowwhere.stocksapi.models.StockCall;
import com.knowwhere.stocksapi.models.StockInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NotificationService
        extends Thread {
      @Autowired
      private StockCallService  stockCallService;

      @Autowired
      private StockInfoService stockInfoService;

      private CommodityController commodityController;
      private List<Commodity> commodities;

      public NotificationService(CommodityController commodityController,
                                 List<Commodity> commodities) {
            this.commodityController = commodityController;
            this.commodities = commodities;
      }

      @Override
      public void run() {
            for (Commodity commodity : this.commodities) {
                  Optional<StockInfo> optionalStockInfo = this.stockInfoService.getByName(commodity.getName());
                  if (optionalStockInfo.isPresent()) {
                        int id = optionalStockInfo.get().getId();
                        List<StockCall> stockCalls = this.stockCallService.getAllByStockId(id);
                        for (StockCall stockCall : stockCalls) {
                              String condition = "";
                              switch (stockCall.getCallType().getName()) {
                                    case "BUY":
                                          if (stockCall.getStopLoss() >= commodity.getPrice()) {
                                                condition = "Stop Loss has reached.";
                                          } else if (stockCall.getTarget1() != 0 &&
                                                  stockCall.getTarget1() <= commodity.getPrice()) {
                                                condition = "Target 1 has been completed.";
                                          } else if (stockCall.getTarget2() != 0 &&
                                                  stockCall.getTarget2() <= commodity.getPrice()) {
                                                condition = "Target 2 has been completed.";
                                          } else if (stockCall.getTarget3() != 0 &&
                                                  stockCall.getTarget3() <= commodity.getPrice()) {
                                                condition = "Target 3 has been completed.";
                                          } else {
                                                break;
                                          }
                                          this.callCompleted(stockCall, condition);
                                          break;

                                    case "SELL":
                                          if (stockCall.getStopLoss() <= commodity.getPrice()) {
                                                condition = "Stop Loss has reached.";
                                          } else if (stockCall.getTarget1() != 0 &&
                                                  stockCall.getTarget1() >= commodity.getPrice()) {
                                                condition = "Target 1 has been completed.";
                                          } else if (stockCall.getTarget2() != 0 &&
                                                  stockCall.getTarget2() >= commodity.getPrice()) {
                                                condition = "Target 2 has been completed.";
                                          } else if (stockCall.getTarget3() != 0 &&
                                                  stockCall.getTarget3() >= commodity.getPrice()) {
                                                condition = "Target 3 has been completed.";
                                          } else
                                                break;
                                          this.callCompleted(stockCall, condition);
                                          break;
                              }
                        }
                  }
            }
      }

      private Notification generateMessage(StockCall stockCall, String condition) {
            return new Notification(stockCall.getStockInfo().getName() + "'s " + stockCall.getCallType().getName() + " CALL COMPLETED",
                    "The call posted on " + stockCall.getCreatedAt() + " has been completed since " + condition);
      }


      private void callCompleted(final StockCall stockCall,
                                 final String condition) {
            StockCall updatedStockCall = this.stockCallService.setCompleted(stockCall);
            this.callUpdated(updatedStockCall);
            Notification notification = this.generateMessage(stockCall, condition);
            this.commodityController.notifyCallCompleted(notification);
      }


      private void callUpdated(final StockCall stockCall) {
            this.commodityController.callUpdated(stockCall);
      }
}
