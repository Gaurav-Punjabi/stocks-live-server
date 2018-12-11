package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.models.CallType;
import com.knowwhere.stocksapi.repositories.CallTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CallTypeService {
      @Autowired
      private CallTypeRepository callTypeRepository;

      public Optional<CallType> getByName(String name) {
            return this.callTypeRepository.findByName(name);
      }

      public Optional<CallType> getById(int id) {
            return this.callTypeRepository.findById(id);
      }
}
