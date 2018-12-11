package com.knowwhere.stocksapi.models.stock_call;

import org.springframework.lang.Nullable;

public class CallWrapper {
      private String name, callType;
      private Double price, stopLoss;
      @Nullable
      private Double target1, target2, target3;

      public CallWrapper() {
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getCallType() {
            return callType;
      }

      public void setCallType(String callType) {
            this.callType = callType;
      }

      public Double getPrice() {
            return price;
      }

      public void setPrice(Double price) {
            this.price = price;
      }

      public Double getStopLoss() {
            return stopLoss;
      }

      public void setStopLoss(Double stopLoss) {
            this.stopLoss = stopLoss;
      }

      public Double getTarget1() {
            return target1;
      }

      public void setTarget1(Double target1) {
            this.target1 = target1;
      }

      public Double getTarget2() {
            return target2;
      }

      public void setTarget2(Double target2) {
            this.target2 = target2;
      }

      public Double getTarget3() {
            return target3;
      }

      public void setTarget3(Double target3) {
            this.target3 = target3;
      }
}
