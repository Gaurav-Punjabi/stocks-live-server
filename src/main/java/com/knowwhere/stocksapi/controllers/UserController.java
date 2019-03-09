package com.knowwhere.stocksapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "users/")
public class UserController {
      @PostMapping("pushToken")
      public ResponseEntity<?> pushToken(@RequestBody PushTokenModel pushTokenModel) {
            System.out.println("pushTokenModel = " + pushTokenModel);
            return ResponseEntity.noContent().build();
      }

      class PushTokenModel {
            private Token token;
            private User user;

            class Token {
                  private String token;

                  public String getToken() {
                        return token;
                  }

                  public void setToken(String token) {
                        this.token = token;
                  }

                  @Override
                  public String toString() {
                        return "Token{" +
                                "token='" + token + '\'' +
                                '}';
                  }
            }
            class User {
                  private String username;

                  public User(String username) {
                        this.username = username;
                  }

                  public String getUsername() {
                        return username;
                  }

                  @Override
                  public String toString() {
                        return "User{" +
                                "username='" + username + '\'' +
                                '}';
                  }

                  public void setUsername(String username) {


                        this.username = username;
                  }

            }

            public Token getToken() {
                  return token;
            }

            public void setToken(Token token) {
                  this.token = token;
            }

            public User getUser() {
                  return user;
            }

            public void setUser(User user) {
                  this.user = user;
            }

            @Override
            public String toString() {
                  return "PushTokenModel{" +
                          "token=" + token +
                          ", user=" + user +
                          '}';
            }
      }
}
