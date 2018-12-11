package com.knowwhere.stocksapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "StockInfo")
public class StockInfo {
      @Id
      @GeneratedValue
      @Column(name = "id")
      private int id;

      @Column(name = "name")
      private String name;

      @JsonIgnore
      @ManyToOne(cascade = CascadeType.ALL)
      @JoinColumn(name = "typeId", referencedColumnName = "id")
      private StockType stockType;

      public StockInfo(final String name,
                       final StockType stockType) {
            this.name = name;
            this.stockType = stockType;
      }

      public StockInfo() {
      }

      public int getId() {
            return id;
      }

      public String getName() {
            return name;
      }

      public StockType getStockType() {
            return stockType;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StockInfo stockInfo = (StockInfo) o;
            return id == stockInfo.id &&
                    Objects.equals(name, stockInfo.name) &&
                    Objects.equals(stockType, stockInfo.stockType);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, name, stockType);
      }

      @Override
      public String toString() {
            return "StockInfoRepository{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", stockType=" + stockType +
                    '}';
      }
}
