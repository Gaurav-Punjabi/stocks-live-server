package com.knowwhere.stocksapi.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class Users extends BaseModel {
      @Id
      @GeneratedValue
      @Column(name = "id")
      private int id;

      @Column(name = "email")
      private String email;

      @Column(name = "notification_token")
      private String notificationToken;

      @Column(name = "access_token")
      private String accessToken;

      @Column(name = "refresh_token")
      private String refreshToken;

      @Column(name = "password")
      private String password;

      @Column(name = "phone_number")
      private String phoneNumber;

      @Column(name = "is_admin")
      private boolean isAdmin;

      public Users(String email, String password, String phoneNumber, boolean isAdmin) {
            this.email = email;
            this.password = password;
            this.isAdmin = isAdmin;
            this.phoneNumber = phoneNumber;
      }

      public boolean isAdmin() {
            return isAdmin;
      }

      public void setAdmin(boolean admin) {
            isAdmin = admin;
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getNotificationToken() {
            return notificationToken;
      }

      public void setNotificationToken(String notificationToken) {
            this.notificationToken = notificationToken;
      }

      public String getAccessToken() {
            return accessToken;
      }

      public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
      }

      public String getRefreshToken() {
            return refreshToken;
      }

      public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
      }

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      public String getPhoneNumber() {
            return phoneNumber;
      }

      public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            Users users = (Users) o;
            return id == users.id &&
                    isAdmin == users.isAdmin &&
                    Objects.equals(email, users.email) &&
                    Objects.equals(notificationToken, users.notificationToken) &&
                    Objects.equals(accessToken, users.accessToken) &&
                    Objects.equals(refreshToken, users.refreshToken) &&
                    Objects.equals(password, users.password) &&
                    Objects.equals(phoneNumber, users.phoneNumber);
      }

      @Override
      public int hashCode() {
            return Objects.hash(super.hashCode(), id, email, notificationToken, accessToken, refreshToken, password, phoneNumber, isAdmin);
      }

      @Override
      public String toString() {
            return "Users{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", notificationToken='" + notificationToken + '\'' +
                    ", accessToken='" + accessToken + '\'' +
                    ", refreshToken='" + refreshToken + '\'' +
                    ", password='" + password + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", isAdmin=" + isAdmin +
                    '}';
      }
}
