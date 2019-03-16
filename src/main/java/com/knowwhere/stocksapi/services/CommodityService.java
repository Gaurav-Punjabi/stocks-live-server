package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.controllers.CommodityController;
import com.knowwhere.stocksapi.models.Commodity;
import com.knowwhere.stocksapi.models.StockCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommodityService {

      @Autowired
      private WebScrapperService webScrapperService;

      @Autowired
      private StockCallService stockCallService;

      @Autowired
      private NotificationService notificationService;


      public List<Commodity> getAllCommodities() {
            return this.webScrapperService.getCommodities();
      }

      public void subscribeUser(final CommodityController userController) {
            CommodityListener commodityListener = new CommodityListener(userController);
            commodityListener.start();
      }



      private class CommodityListener extends Thread {
            private CommodityController user;
            private Set<Commodity> commoditySet;

            private CommodityListener(CommodityController user) {
                  this.user = user;
                  this.commoditySet = new HashSet<>(CommodityService.this.getAllCommodities());
            }

            @Override
            public void run() {
                  try {
                        while (true) {
                              Thread.sleep(500);
                              Set<Commodity> commodities = new HashSet<>(CommodityService.this.getAllCommodities());

                              Set<Commodity> updatedCommodities = new HashSet<>();
                              Set<Commodity> oldCommodities = new HashSet<>();

                              oldCommodities.addAll(commodities);
                              updatedCommodities.addAll(commodities);


                              oldCommodities.retainAll(this.commoditySet);
                              updatedCommodities.removeAll(oldCommodities);

                              if(updatedCommodities.size() > 0) {
                                    // Checking if any of the stock call is satisfied or not
                                    this.verifyStockCall(updatedCommodities);
                                    this.user.notifyUpdatedRates(updatedCommodities);
                                    this.commoditySet = commodities;
                              }
                        }
                  } catch(InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                  }
            }

            private void verifyStockCall(Set<Commodity> commodities) {
                  List<StockCall> stockCalls = stockCallService.getAllStockCalls();
                  for(Commodity commodity : commodities) {
                        String commodityName = commodity.getName();
                        int index = Collections.binarySearch(stockCalls, commodityName);
                        if(index >= 0) {
                              StockCall stockCall = stockCalls.get(index);
                              switch (stockCall.getCallType().getName()) {
                                    case "BUY" :
                                          if(stockCall.getTarget1()  >= commodity.getPrice()) {
                                                System.out.println("Buy Call target 1 reached");
                                                String title = new StringBuilder("Target 1 of ")
                                                                               .append(stockCall.getStockInfo().getName())
                                                                               .append(" reached.").toString();
                                                String message = new StringBuilder("Target 1 (₹")
                                                                                    .append(stockCall.getTarget1())
                                                                                    .append(") of ")
                                                                                    .append(stockCall.getStockInfo().getName())
                                                                                    .append(" placed on ")
                                                                                    .append(stockCall.getCreatedAt())
                                                                                    .append(" is completed").toString();
                                                notificationService.pushNotificationToAll(title, message);
                                          }
                                          if(stockCall.getTarget2() >= commodity.getPrice()) {
                                                System.out.println("Buy Call target 2 reached");
                                                String title = new StringBuilder("Target 1 of ")
                                                        .append(stockCall.getStockInfo().getName())
                                                        .append(" reached.").toString();
                                                String message = new StringBuilder("Target 1 (₹")
                                                        .append(stockCall.getTarget1())
                                                        .append(") of ")
                                                        .append(stockCall.getStockInfo().getName())
                                                        .append(" placed on ")
                                                        .append(stockCall.getCreatedAt())
                                                        .append(" is completed").toString();
                                                notificationService.pushNotificationToAll(title, message);
                                          }
                                          if(stockCall.getTarget3() >= commodity.getPrice()) {
                                                System.out.println("Buy Call target 3 reached");
                                                String title = new StringBuilder("Target 1 of ")
                                                        .append(stockCall.getStockInfo().getName())
                                                        .append(" reached.").toString();
                                                String message = new StringBuilder("Target 1 (₹")
                                                        .append(stockCall.getTarget1())
                                                        .append(") of ")
                                                        .append(stockCall.getStockInfo().getName())
                                                        .append(" placed on ")
                                                        .append(stockCall.getCreatedAt())
                                                        .append(" is completed").toString();
                                                notificationService.pushNotificationToAll(title, message);
                                          }
                                          break;

                                    case "SELL" :

                                          break;

                                    default:
                                          break;
                              }
                        }
                  }
            }
      }
}
