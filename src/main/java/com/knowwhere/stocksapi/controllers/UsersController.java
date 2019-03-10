package com.knowwhere.stocksapi.controllers;

import com.knowwhere.stocksapi.exceptions.NonUniqueFieldException;
import com.knowwhere.stocksapi.models.Users;
import com.knowwhere.stocksapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/users/")
public class UsersController {

      @Autowired
      private UsersService usersService;

      /**
       * Endpoint for registration of admin.
       * @param registerModel : The input json format
       * @return The created admin
       */
      @PostMapping("admin/register")
      public ResponseEntity<?> registerAdmin(@RequestBody RegisterModel registerModel) {
            String email = registerModel.getEmail();
            String password = registerModel.getPassword();
            String phoneNumber = registerModel.getPhoneNumber();

            try {
                  Users users = this.usersService.registerAdmin(email, password, phoneNumber);
                  return ResponseEntity.ok(users);
            } catch (IllegalArgumentException iae) {
                  return ResponseEntity.unprocessableEntity().body(iae.getMessage());
            } catch (NonUniqueFieldException nufe) {
                  return ResponseEntity.status(409).body(nufe.getMessage());
            }
      }

      @PostMapping("register")
      public ResponseEntity<?> registerUser(@RequestBody RegisterModel registerModel) {
            String email = registerModel.getEmail();
            String password = registerModel.getPassword();
            String phoneNumber = registerModel.getPhoneNumber();

            try {
                  Users users = this.usersService.registerUser(email, password, phoneNumber);
                  return ResponseEntity.ok(users);
            } catch (IllegalArgumentException iae) {
                  return ResponseEntity.unprocessableEntity().body(iae.getMessage());
            } catch (NonUniqueFieldException nufe) {
                  return ResponseEntity.status(409).body(nufe.getMessage());
            }
      }

      /**
       *
       * @param pushTokenModel
       * @return
       */
      @PostMapping("pushToken")
      public ResponseEntity<?> pushToken(@RequestBody PushTokenModel pushTokenModel) {
            System.out.println("pushTokenModel = " + pushTokenModel);
            return ResponseEntity.noContent().build();
      }

      static class RegisterModel {
            private String email, phoneNumber, password;

            public RegisterModel() {}

            public RegisterModel(String email, String phoneNumber, String password) {
                  this.email = email;
                  this.phoneNumber = phoneNumber;
                  this.password = password;
            }

            public String getEmail() {
                  return email;
            }

            public void setEmail(String email) {
                  this.email = email;
            }

            public String getPhoneNumber() {
                  return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                  this.phoneNumber = phoneNumber;
            }

            public String getPassword() {
                  return password;
            }

            public void setPassword(String password) {
                  this.password = password;
            }
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
