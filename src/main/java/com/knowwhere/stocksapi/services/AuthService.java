package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.models.Users;
import com.knowwhere.stocksapi.repositories.UsersRepository;
import com.sun.javafx.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class AuthService {
      @Autowired
      private UsersService usersService;
      @Autowired
      private UsersRepository usersRepository;
      @Autowired
      private MessageService messageService;
      @Autowired
      private HashService hashService;

      /**
       * Generates a random otp and sends a message to the user.
       * @param phoneNumber The phone number of the user who wants the otp.
       * @return The success message.
       * @throws IllegalArgumentException If the given phone number is invalid.
       * @throws NoSuchFieldException If the given phone number does not belongs to any current user in the database.
       */
      public String sendOtp(String phoneNumber) throws IllegalArgumentException, NoSuchFieldException {
            if(!phoneNumber.matches("^\\d{10}$"))
                  throw new IllegalArgumentException("Please provide a valid phone number.");
            Users users = this.usersRepository.findByPhoneNumber(phoneNumber).orElse(null);
            if(users == null)
                  throw new NoSuchFieldException("No user with this mobile number present");

            // Sending the otp to the user
            String otp = this.generateOtp();
            this.messageService.sendMessage(otp, "Your otp is : " + otp);

            users.setOtp(otp);
            this.usersRepository.save(users);

            return "OTP Sent successfully";
      }


      public boolean verifyOtp(String phoneNumber, String otp) throws IllegalArgumentException, NoSuchFieldException {
            if(phoneNumber == null || !phoneNumber.matches("^\\d{10}$"))
                  throw new IllegalArgumentException("Please provide a valid phone number.");
            if(otp == null || !otp.matches("^\\d{10}$"))
                  throw new IllegalArgumentException("Please provide a valid otp");
            Users users = this.usersRepository.findByPhoneNumber(phoneNumber).orElse(null);
            if(users == null)
                  throw new NoSuchFieldException("No user with this mobile number present");

            String sentOtp = users.getOtp();
            return sentOtp.equals(otp);
      }

      public boolean login(String email,
                           String password) throws IllegalArgumentException, NoSuchFieldException {
            if(email == null || !email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-\\+]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
                  throw new IllegalArgumentException("Please provide a valid email id");
            if(password == null || password.length() < 8)
                  throw new IllegalArgumentException("Please provide a valid password");
            Users users = this.usersRepository.findByEmail(email).orElse(null);
            if(users == null)
                  throw new NoSuchFieldException("No user with the given email id found");

            try {
                  String hashedPassword = this.hashService.stringToSha1(password);
                  return hashedPassword.equals(users.getPassword());
            } catch (NoSuchAlgorithmException nsae) {
                  return false;
            }
      }

      private String generateOtp() {
            Random random = new Random();
            String otp = "";
            for(int i=0;i<6;i++)
                  otp += random.nextInt(9);
            return otp;
      }
}
