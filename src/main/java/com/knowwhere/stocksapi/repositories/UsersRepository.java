package com.knowwhere.stocksapi.repositories;

import com.knowwhere.stocksapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {}
