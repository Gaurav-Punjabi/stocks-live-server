package com.knowwhere.stocksapi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class Users {
      @Id
      @GeneratedValue
      @Column(name = "id")
      private int id;

      @Column(name = "username")
      private String username;

      @Column(name = "token")
      private String token;

      public Users(String username, String token) {
            this.username = username;
            this.token = token;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getUsername() {
            return username;
      }

      public void setUsername(String username) {
            this.username = username;
      }

      public String getToken() {
            return token;
      }

      public void setToken(String token) {
            this.token = token;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Users users = (Users) o;
            return id == users.id &&
                    Objects.equals(username, users.username) &&
                    Objects.equals(token, users.token);
      }

      @Override
      public int hashCode() {
            return Objects.hash(id, username, token);
      }

      @Override
      public String toString() {
            return "Users{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", token='" + token + '\'' +
                    '}';
      }
}
