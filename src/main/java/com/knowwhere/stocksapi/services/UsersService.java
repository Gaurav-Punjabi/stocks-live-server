package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.models.Users;
import com.knowwhere.stocksapi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UsersService {
      @Autowired
      private UsersRepository usersRepository;
      @Autowired
      private HashService hashService;

      public Users registerAdmin(String email,
                                 String password,
                                 String phoneNumber) {
            try {
                  String hashedPassword = this.hashService.stringToSha1(password);
                  Users admin = new Users(email, hashedPassword, phoneNumber, true);
                  return this.usersRepository.save(admin);
            } catch (NoSuchAlgorithmException nsae) {
                  return null;
            }
      }

      public Users registerUser(String email,
                                String password,
                                String phoneNumber) {
            try {
                  String hashedPassword = this.hashService.stringToSha1(password);
                  Users users = new Users(email, hashedPassword, phoneNumber, false);
                  return this.usersRepository.save(users);
            } catch (NoSuchAlgorithmException nsae) {
                  return null;
            }
      }

      public List<Users> getAll() {
            return this.usersRepository.findAll();
      }
}
