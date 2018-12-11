package com.knowwhere.stocksapi.controllers;

import com.knowwhere.stocksapi.models.*;
import com.knowwhere.stocksapi.services.CommodityService;
import com.knowwhere.stocksapi.services.StockCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
public class CommodityController {

      @Autowired
      private CommodityService commodityService;

      @Autowired
      private StockCallService stockCallService;

      @Autowired
      private SimpMessagingTemplate simpMessagingTemplate;

      @MessageMapping("/openConnection")
      @SendTo("/operation/rateUpdated")
      public List<Commodity> openConnection() {
            System.out.println("Called");
            this.commodityService.subscribeUser(this);
            return this.commodityService.getAllCommodities();
      }


      public void notifyUpdatedRates(Set<Commodity> commoditySet) {
            List<Commodity> commodities = new ArrayList<>(commoditySet);
            this.simpMessagingTemplate.convertAndSend("/operation/rateUpdated", commodities);
      }

      public void callUpdated(final StockCall stockCall) {
            this.simpMessagingTemplate.convertAndSend("/operation/callUpdated", stockCall);
      }

      public void notifyCallCompleted(final Notification notification) {
            this.simpMessagingTemplate.convertAndSend("/operation/callCompleted", notification);
      }
}
