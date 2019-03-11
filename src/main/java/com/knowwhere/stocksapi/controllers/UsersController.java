package com.knowwhere.stocksapi.controllers;

import com.knowwhere.stocksapi.exceptions.NonUniqueFieldException;
import com.knowwhere.stocksapi.models.Users;
import com.knowwhere.stocksapi.services.NotificationService;
import com.knowwhere.stocksapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/users/")
public class UsersController {

      @Autowired
      private UsersService usersService;
      @Autowired
      private NotificationService notificationService;

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
            String token = pushTokenModel.token.value;
            String phoneNumber = pushTokenModel.user.username;

            this.usersService.pushToken(phoneNumber, token);

            return ResponseEntity.ok("{}");
      }

      @GetMapping("/test")
      public ResponseEntity<?> test() {
            this.notificationService.pushNotificationToAll("Hello World", "Call Placed");
            return ResponseEntity.ok(null);
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

      static class PushTokenModel {
            private Token token;
            private User user;

            public PushTokenModel() {}

            static class Token {
                  private String value;
                  public Token() {}

                  public Token(String value) {
                        this.value = value;
                  }

                  public String getValue() {
                        return value;
                  }

                  public void setValue(String value) {
                        this.value = value;
                  }

                  @Override
                  public String toString() {
                        return "Token{" +
                                "value='" + value + '\'' +
                                '}';
                  }
            }
            static class User {
                  private String username;

                  public User() {}

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
                          "value=" + token +
                          ", user=" + user +
                          '}';
            }
      }
}
