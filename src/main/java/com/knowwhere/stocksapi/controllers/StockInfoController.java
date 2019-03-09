package com.knowwhere.stocksapi.controllers;

import com.knowwhere.stocksapi.services.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "stock-info/")
public class StockInfoController {
      @Autowired
      private StockInfoService stockInfoService;

}
