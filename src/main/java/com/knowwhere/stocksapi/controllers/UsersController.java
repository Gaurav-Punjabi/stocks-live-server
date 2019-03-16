package com.knowwhere.stocksapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.knowwhere.stocksapi.exceptions.NonUniqueFieldException;
import com.knowwhere.stocksapi.models.Users;
import com.knowwhere.stocksapi.models.datatables.DataTableRequest;
import com.knowwhere.stocksapi.models.datatables.DataTableResults;
import com.knowwhere.stocksapi.models.users.UsersTableWrapper;
import com.knowwhere.stocksapi.services.NotificationService;
import com.knowwhere.stocksapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@CrossOrigin(origins = "*")
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
                  Users users = this.usersService.registerAdmin(registerModel.name, email, password, phoneNumber);
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
                  Users users = this.usersService.registerUser(registerModel.name, email, password, phoneNumber);
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
            String email = pushTokenModel.user.email;

            this.usersService.pushToken(email, token);

            return ResponseEntity.ok("{}");
      }

      @GetMapping("/test")
      public ResponseEntity<?> test() {
            this.notificationService.pushNotificationToAll("Hello World", "Call Placed");
            return ResponseEntity.ok(null);
      }

      @RequestMapping(value = "/getUsersTable", method = RequestMethod.GET)
      @ResponseBody
      public ResponseEntity<?> getWarehouses(HttpServletRequest request,
                                             HttpServletResponse response,
                                             Model model) throws JsonProcessingException {
            DataTableRequest<UsersTableWrapper> dataTableInRQ = new DataTableRequest<>(request);
            DataTableResults<UsersTableWrapper> dataTableResults = this.usersService.getUsersTable(dataTableInRQ);
            return ResponseEntity.ok(new Gson().toJson(dataTableResults));
      }

      static class RegisterModel {
            private String name, email, phoneNumber, password;

            public RegisterModel() {}

            public String getName() {
                  return name;
            }

            public void setName(String name) {
                  this.name = name;
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
                  private String email;

                  public User() {}

                  public User(String email) {
                        this.email = email;
                  }

                  public String getEmail() {
                        return email;
                  }

                  @Override
                  public String toString() {
                        return "User{" +
                                "email='" + email + '\'' +
                                '}';
                  }

                  public void setEmail(String email) {


                        this.email = email;
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
