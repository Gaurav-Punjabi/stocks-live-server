package com.knowwhere.stocksapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "StockCall")
public class StockCall
        extends BaseModel {
      @Id
      @GeneratedValue
      @Column(name = "id")
      private int id;

//      @JsonIgnore
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name = "StockId", referencedColumnName = "id")
      private StockInfo stockInfo;

//      @JsonIgnore
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name = "TypeId", referencedColumnName = "id")
      private CallType callType;

      @Column(name = "price")
      private double price;

      @Column(name = "stopLoss")
      private double stopLoss;

      @Column(name = "target1")
      private double target1;

      @Column(name = "target2")
      private double target2;

      @Column(name = "target3")
      private double target3;

      @Column(name = "completed", columnDefinition = "TINYINT")
      @Type(type = "org.hibernate.type.NumericBooleanType")
      private boolean completed = false;

      public StockCall(StockInfo stockInfo,
                       CallType callType,
                       double price,
                       double stopLoss,
                       double target1,
                       double target2,
                       double target3) {
            this.stockInfo = stockInfo;
            this.price = price;
            this.stopLoss = stopLoss;
            this.target1 = target1;
            this.target2 = target2;
            this.target3 = target3;
      }

      public StockCall() {
      }

      public void setId(int id) {
            this.id = id;
      }

      public void setStockInfo(StockInfo stockInfo) {
            this.stockInfo = stockInfo;
      }

      public void setCallType(CallType callType) {
            this.callType = callType;
      }

      public void setPrice(double price) {
            this.price = price;
      }

      public void setStopLoss(double stopLoss) {
            this.stopLoss = stopLoss;
      }

      public void setTarget1(double target1) {
            this.target1 = target1;
      }

      public void setTarget2(double target2) {
            this.target2 = target2;
      }

      public void setTarget3(double target3) {
            this.target3 = target3;
      }

      public void setCompleted(boolean completed) {
            this.completed = completed;
      }

      public int getId() {
            return id;
      }

      public StockInfo getStockInfo() {
            return stockInfo;
      }

      public CallType getCallType() {
            return callType;
      }

      public double getPrice() {
            return price;
      }

      public double getStopLoss() {
            return stopLoss;
      }

      public double getTarget1() {
            return target1;
      }

      public double getTarget2() {
            return target2;
      }

      public double getTarget3() {
            return target3;
      }

      public boolean isCompleted() {
            return completed;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            StockCall stockCall = (StockCall) o;
            return id == stockCall.id &&
                    Double.compare(stockCall.price, price) == 0 &&
                    Double.compare(stockCall.stopLoss, stopLoss) == 0 &&
                    Double.compare(stockCall.target1, target1) == 0 &&
                    Double.compare(stockCall.target2, target2) == 0 &&
                    Double.compare(stockCall.target3, target3) == 0 &&
                    completed == stockCall.completed &&
                    Objects.equals(stockInfo, stockCall.stockInfo) &&
                    Objects.equals(callType, stockCall.callType);
      }

      @Override
      public int hashCode() {
            return Objects.hash(super.hashCode(), id, stockInfo, callType, price, stopLoss, target1, target2, target3, completed);
      }

      @Override
      public String toString() {
            return "StockCall{" +
                    "id=" + id +
                    ", stockInfo=" + stockInfo +
                    ", callType=" + callType +
                    ", price=" + price +
                    ", stopLoss=" + stopLoss +
                    ", target1=" + target1 +
                    ", target2=" + target2 +
                    ", target3=" + target3 +
                    ", completed=" + completed +
                    '}';
      }
}
