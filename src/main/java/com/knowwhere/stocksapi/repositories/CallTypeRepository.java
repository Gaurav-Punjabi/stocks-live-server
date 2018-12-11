package com.knowwhere.stocksapi.repositories;

import com.knowwhere.stocksapi.models.CallType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CallTypeRepository
        extends JpaRepository<CallType, Integer> {
      Optional<CallType> findByName(String name);
}
