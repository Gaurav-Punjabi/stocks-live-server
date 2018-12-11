package com.knowwhere.stocksapi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CallType")
public class CallType {
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

      public CallType(String name) {
            this.name = name;
      }

      public CallType() {
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CallType callType = (CallType) o;
            return id == callType.id &&
                    Objects.equals(name, callType.name);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, name);
      }

      @Override
      public String toString() {
            return "CallType{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
      }
}
