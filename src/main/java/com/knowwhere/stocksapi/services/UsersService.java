package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.exceptions.NonUniqueFieldException;
import com.knowwhere.stocksapi.models.Users;
import com.knowwhere.stocksapi.repositories.UsersRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UsersService {
      @Autowired
      private UsersRepository usersRepository;
      @Autowired
      private HashService hashService;

      /**
       * A method to insert a user into the database
       * @param email Email ID of the user
       * @param password Password of the user (Must be 8 digits long)
       * @param phoneNumber Phone number of the user (Must be 10 digits long)
       * @param isAdmin Whether the user is admin or not (true/false)
       * @return Object of the User that has been inserted
       * @throws IllegalArgumentException If any of the argument is invalid or null
       * @throws NonUniqueFieldException If any of the given argument is not unique in the database
       */
      public Users store(String email,
                         String password,
                         String phoneNumber,
                         boolean isAdmin) throws IllegalArgumentException, NonUniqueFieldException {
            // Validating the input fields
            if(email == null) {
                  throw new IllegalArgumentException("Please provide a email id.");
            } else if(password == null) {
                  throw new IllegalArgumentException("Please provide a password.");
            } else if(phoneNumber == null || !(phoneNumber.matches("^\\d{10}$"))) {
                  throw new IllegalArgumentException("Please provide a valid phoneNumber");
            } else if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-\\+]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                  throw new IllegalArgumentException("Not a valid email");
            } else if(password.length() < 8) {
                  throw new IllegalArgumentException("Password must be 8 characters long.");
            } else if(phoneNumber.length() != 10) {
                  throw new IllegalArgumentException("Phone Number is not valid");
            } else if(this.usersRepository.findByEmail(email).isPresent()) {
                  throw new NonUniqueFieldException("Given email already exists");
            } else if(this.usersRepository.findByPhoneNumber(phoneNumber).isPresent()) {
                  throw new NonUniqueFieldException("Given phone number already exists");
            }

            try {
                  String hashedPassword = this.hashService.stringToSha1(password);
                  // Checking if the password is already present or not
                  if(this.usersRepository.findByPassword(hashedPassword).isPresent())
                        throw new NonUniqueFieldException("Given password is already taken.");

                  Users admin = new Users(email, hashedPassword, phoneNumber, isAdmin);
                  return this.usersRepository.save(admin);
            } catch (NoSuchAlgorithmException nsae) {
                  return null;
            }
      }

      public Users registerAdmin(String email,
                                 String password,
                                 String phoneNumber) throws IllegalArgumentException, NonUniqueFieldException {
            return this.store(email, password, phoneNumber, true);
      }

      public Users registerUser(String email,
                                String password,
                                String phoneNumber) throws IllegalArgumentException, NonUniqueFieldException {
            return this.store(email, password, phoneNumber, false);
      }

      public List<Users> getAll() {
            return this.usersRepository.findAll();
      }
}
