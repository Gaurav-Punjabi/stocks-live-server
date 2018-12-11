package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.controllers.CommodityController;
import com.knowwhere.stocksapi.models.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommodityService {

      @Autowired
      WebScrapperService webScrapperService;

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

                                    this.user.notifyUpdatedRates(updatedCommodities);
                                    this.commoditySet = commodities;
                              }
                        }
                  } catch(InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                  }
            }
      }
}
