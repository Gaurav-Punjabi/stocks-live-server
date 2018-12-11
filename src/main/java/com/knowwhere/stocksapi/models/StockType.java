package com.knowwhere.stocksapi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "StockType")
public class StockType {
      @Id
      @GeneratedValue
      @Column(name = "id")
      private int id;

      @Column(name = "name")
      private String name;

      public int getId() {
            return id;
      }

      public String getName() {
            return name;
      }

      public StockType(String name) {
            this.name = name;
      }

      public StockType() {

      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StockType stockType = (StockType) o;
            return id == stockType.id &&
                    Objects.equals(name, stockType.name);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, name);
      }

      @Override
      public String toString() {
            return "StockType{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
      }
}
