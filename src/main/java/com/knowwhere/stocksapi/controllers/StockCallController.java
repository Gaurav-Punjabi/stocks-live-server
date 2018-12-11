package com.knowwhere.stocksapi.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knowwhere.stocksapi.exceptions.FieldNotFoundException;
import com.knowwhere.stocksapi.models.StockCall;
import com.knowwhere.stocksapi.models.StockInfo;
import com.knowwhere.stocksapi.models.StockType;
import com.knowwhere.stocksapi.models.stock_call.CallWrapper;
import com.knowwhere.stocksapi.models.stock_call.StockCallResponse;
import com.knowwhere.stocksapi.services.StockCallService;
import com.knowwhere.stocksapi.services.StockInfoService;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/call")
public class StockCallController {

      @Autowired
      private StockInfoService stockInfoService;

      @Autowired
      private StockCallService stockCallService;

      @GetMapping("/getAll")
      public ResponseEntity<?> openCallConnection() {
            List<StockCall> stockCalls = this.stockCallService.getAllStockCalls();
            System.out.println("stockCalls = " + stockCalls);
            List<StockCallResponse> stockCallResponses = new ArrayList<>();
            for(StockCall stockCall : stockCalls) {
                  StockCallResponse stockCallResponse = new StockCallResponse(stockCall.getStockInfo().getName(),
                                                                              stockCall.getCallType().getName(),
                                                                              stockCall.getPrice(),
                                                                              stockCall.getTarget1(),
                                                                              stockCall.getTarget2(),
                                                                              stockCall.getTarget3(),
                                                                              stockCall.getStopLoss(),
                                                                              stockCall.isCompleted());
                  stockCallResponses.add(stockCallResponse);
            }
            return ResponseEntity.ok(stockCallResponses);
      }

      @PostMapping("/add")
      public ResponseEntity<?> add(@RequestBody CallWrapper callWrapper) throws FieldNotFoundException {
            if(callWrapper.getTarget1() == null && callWrapper.getTarget2()== null && callWrapper.getTarget3() == null) {
                  return ResponseEntity.unprocessableEntity()
                          .body("At least one target must be provided to add a stock call");
            }
            StockCall stockCall = this.stockCallService.add(callWrapper.getName(),
                                                            callWrapper.getCallType(),
                                                            callWrapper.getPrice(),
                                                            callWrapper.getStopLoss(),
                                                            (callWrapper.getTarget1() == null) ? 0 : callWrapper.getTarget1(),
                                                            (callWrapper.getTarget2() == null) ? 0 : callWrapper.getTarget2(),
                                                            (callWrapper.getTarget3() == null) ? 0 : callWrapper.getTarget3());
            System.out.println("stockCall added = " + stockCall);
            return ResponseEntity.ok(stockCall);
      }
}
