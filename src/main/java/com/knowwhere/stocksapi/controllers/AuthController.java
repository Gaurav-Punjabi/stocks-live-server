package com.knowwhere.stocksapi.controllers;

import com.knowwhere.stocksapi.exceptions.FieldNotFoundException;
import com.knowwhere.stocksapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(BASE_URL + "/auth/")
public class AuthController {
      @Autowired
      private AuthService authService;

      @PostMapping("sendOtp/{phoneNumber}")
      public ResponseEntity<?> sendOtp(@NotNull @PathVariable("phoneNumber") String phoneNumber) {
            try {
                  String message =  this.authService.sendOtp(phoneNumber);
                  return ResponseEntity.ok(message);
            } catch (Exception e) {
                  return ResponseEntity.unprocessableEntity().body(e.getMessage());
            }
      }

      @PostMapping("verifyOtp/{phoneNumber}/{otp}")
      public ResponseEntity<?> verifyOtp(@NotNull @PathVariable("phoneNumber") String phoneNumber,
                                         @NotNull @PathVariable("otp") String otp) {
            try {
                  if(this.authService.verifyOtp(phoneNumber, otp))
                        return ResponseEntity.accepted().body("OTP Verified");
                  return ResponseEntity.ok("OTP incorrect");
            } catch (Exception e) {
                  return ResponseEntity.unprocessableEntity().body(e.getMessage());
            }
      }

      @PostMapping("login")
      public ResponseEntity<?> login(@RequestBody LoginRequestModel loginRequestModel) {
            String email = loginRequestModel.email;
            String password = loginRequestModel.password;

            try {
                  if(this.authService.login(email, password))
                        return ResponseEntity.ok("User Verified");
                  return ResponseEntity.ok("User Authentication Failed");
            } catch (Exception e) {
                  return ResponseEntity.unprocessableEntity().body(e.getMessage());
            }
      }

      static class LoginRequestModel {
            private String email, password;

            LoginRequestModel() {
            }

            public String getEmail() {
                  return email;
            }

            public void setEmail(String email) {
                  this.email = email;
            }

            public String getPassword() {
                  return password;
            }

            public void setPassword(String password) {
                  this.password = password;
            }
      }
}
