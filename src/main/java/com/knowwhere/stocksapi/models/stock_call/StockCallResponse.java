package com.knowwhere.stocksapi.models.stock_call;

public class StockCallResponse {
      private String name, type;
      private Double price, target1, target2, target3, stopLoss;
      private boolean status;

      public StockCallResponse(String name, String type, Double price, Double target1, Double target2, Double target3, Double stopLoss, boolean status) {
            this.name = name;
            this.type = type;
            this.price = price;
            this.target1 = target1;
            this.target2 = target2;
            this.target3 = target3;
            this.stopLoss = stopLoss;
            this.status = status;
      }


      public StockCallResponse() {
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getType() {
            return type;
      }

      public void setType(String type) {
            this.type = type;
      }

      public Double getPrice() {
            return price;
      }

      public void setPrice(Double price) {
            this.price = price;
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

      public Double getStopLoss() {
            return stopLoss;
      }

      public void setStopLoss(Double stopLoss) {
            this.stopLoss = stopLoss;
      }

      public boolean isStatus() {
            return status;
      }

      public void setStatus(boolean status) {
            this.status = status;
      }
}
