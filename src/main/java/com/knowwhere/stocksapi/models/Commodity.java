package com.knowwhere.stocksapi.models;

import java.util.Objects;

public class Commodity {
      private String name;
      private Double price;

      public Commodity(String name, Double price) {
            this.name = name;
            this.price = price;
      }

      public String getName() {
            return name;
      }

      public Double getPrice() {
            return price;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Commodity commodity = (Commodity) o;
            return Objects.equals(name, commodity.name);
      }

      @Override
      public int hashCode() {
            return Objects.hash(name);
      }

      @Override
      public String toString() {
            return "Commodity{" +
                    "name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    '}';
      }
}
