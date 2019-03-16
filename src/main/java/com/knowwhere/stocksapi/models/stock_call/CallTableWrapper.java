package com.knowwhere.stocksapi.models.stock_call;

public class CallTableWrapper {
      private String type, name, price, callPrice, target1, target2, target3, stopLost, placedOn;

      public CallTableWrapper(String type, String name, String price, String callPrice, String target1, String target2, String target3, String stopLost, String placedOn) {
            this.type = type;
            this.name = name;
            this.price = price;
            this.callPrice = callPrice;
            this.target1 = target1;
            this.target2 = target2;
            this.target3 = target3;
            this.stopLost = stopLost;
            this.placedOn = placedOn;
      }

      public String getType() {
            return type;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getPrice() {
            return price;
      }

      public void setPrice(String price) {
            this.price = price;
      }

      public String getCallPrice() {
            return callPrice;
      }

      public void setCallPrice(String callPrice) {
            this.callPrice = callPrice;
      }

      public String getTarget1() {
            return target1;
      }

      public void setTarget1(String target1) {
            this.target1 = target1;
      }

      public String getTarget2() {
            return target2;
      }

      public void setTarget2(String target2) {
            this.target2 = target2;
      }

      public String getTarget3() {
            return target3;
      }

      public void setTarget3(String target3) {
            this.target3 = target3;
      }

      public String getStopLost() {
            return stopLost;
      }

      public void setStopLost(String stopLost) {
            this.stopLost = stopLost;
      }

      public String getPlacedOn() {
            return placedOn;
      }

      public void setPlacedOn(String placedOn) {
            this.placedOn = placedOn;
      }
}
