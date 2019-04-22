package com.knowwhere.stocksapi.models.stock_call;

public class CallTableWrapper {
      private int id;
      private String type, name, placedOn;
      private double price, stopLoss, target1, target2, target3;

      public CallTableWrapper(int id,
                              String type,
                              String name,
                              String placedOn,
                              double price,
                              double stopLoss,
                              double target1,
                              double target2,
                              double target3) {
            this.id = id;
            this.type = (type.equals("BUY")) ? "<span class=\"label label-primary\"> BUY </span>" : "<span class=\"label label-success\"> SELL </span>";
            this.name = name;
            this.placedOn = placedOn;
            this.price = price;
            this.stopLoss = stopLoss;
            this.target1 = target1;
            this.target2 = target2;
            this.target3 = target3;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getType() {
            return type;
      }

      public void setType(String type) {
            this.type = type;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getPlacedOn() {
            return placedOn;
      }

      public void setPlacedOn(String placedOn) {
            this.placedOn = placedOn;
      }

      public double getPrice() {
            return price;
      }

      public void setPrice(double price) {
            this.price = price;
      }

      public double getStopLoss() {
            return stopLoss;
      }

      public void setStopLoss(double stopLoss) {
            this.stopLoss = stopLoss;
      }

      public double getTarget1() {
            return target1;
      }

      public void setTarget1(double target1) {
            this.target1 = target1;
      }

      public double getTarget2() {
            return target2;
      }

      public void setTarget2(double target2) {
            this.target2 = target2;
      }

      public double getTarget3() {
            return target3;
      }

      public void setTarget3(double target3) {
            this.target3 = target3;
      }
}
