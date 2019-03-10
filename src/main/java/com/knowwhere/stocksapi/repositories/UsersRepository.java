package com.knowwhere.stocksapi.repositories;

import com.knowwhere.stocksapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
      Optional<Users> findByEmail(String email);
      Optional<Users> findByPhoneNumber(String phoneNumber);
      Optional<Users> findByPassword(String password);
}
