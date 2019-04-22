package com.knowwhere.stocksapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.knowwhere.stocksapi.exceptions.FieldNotFoundException;
import com.knowwhere.stocksapi.models.StockCall;
import com.knowwhere.stocksapi.models.datatables.DataTableRequest;
import com.knowwhere.stocksapi.models.datatables.DataTableResults;
import com.knowwhere.stocksapi.models.stock_call.CallTableWrapper;
import com.knowwhere.stocksapi.models.stock_call.CallWrapper;
import com.knowwhere.stocksapi.models.stock_call.StockCallResponse;
import com.knowwhere.stocksapi.models.users.UsersTableWrapper;
import com.knowwhere.stocksapi.services.NotificationService;
import com.knowwhere.stocksapi.services.StockCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.knowwhere.stocksapi.constants.ControllerConstants.BASE_URL;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(BASE_URL + "/call")
public class StockCallController {

      @Autowired
      private StockCallService stockCallService;

      @Autowired
      private NotificationService notificationService;

      @GetMapping("/getAll")
      public ResponseEntity<?> getAll() {
            List<StockCall> stockCalls = this.stockCallService.getAllStockCalls();
            System.out.println("stockCalls = " + stockCalls);
            List<StockCallResponse> stockCallResponses = new ArrayList<>();
            for (StockCall stockCall : stockCalls) {
                  StockCallResponse stockCallResponse = new StockCallResponse(stockCall.getStockInfo().getName(),
                          stockCall.getCallType().getName(),
                          stockCall.getPrice(),
                          stockCall.getTarget1(),
                          stockCall.getTarget2(),
                          stockCall.getTarget3(),
                          stockCall.getStopLoss(),
                          new SimpleDateFormat("dd-MM-yyyy").format(stockCall.getCreatedAt()),
                          stockCall.isCompleted());
                  stockCallResponses.add(stockCallResponse);
            }
            Collections.reverse(stockCallResponses);
            return ResponseEntity.ok(stockCallResponses);
      }

      @PostMapping("/add")
      public ResponseEntity<?> add(@RequestBody CallWrapper callWrapper) {
            System.out.println("callWrapper = " + callWrapper);
            if (callWrapper.getTarget1() == null && callWrapper.getTarget2() == null && callWrapper.getTarget3() == null) {
                  return ResponseEntity.unprocessableEntity()
                          .body("At least one target must be provided to add a stock call");
            }
            try {
                  StockCall stockCall = this.stockCallService.add(callWrapper.getName(),
                          callWrapper.getCallType(),
                          callWrapper.getPrice(),
                          callWrapper.getStopLoss(),
                          (callWrapper.getTarget1() == null) ? 0 : callWrapper.getTarget1(),
                          (callWrapper.getTarget2() == null) ? 0 : callWrapper.getTarget2(),
                          (callWrapper.getTarget3() == null) ? 0 : callWrapper.getTarget3());

                  String title = "Stock call for " + callWrapper.getName() + " added.";
                  String message = "A stock call for " + callWrapper.getName() + " is added with a stop loss of " + callWrapper.getStopLoss();
                  this.notificationService.pushNotificationToAll(title, message);

                  System.out.println("stockCall added = " + stockCall);
                  return ResponseEntity.ok(stockCall);
            } catch (FieldNotFoundException fnfe) {
                  return ResponseEntity.noContent().build();
            }
      }

      @RequestMapping(value = "/getCallsTable", method = RequestMethod.GET)
      @ResponseBody
      public ResponseEntity<?> getCallsTable(HttpServletRequest request,
                                             HttpServletResponse response,
                                             Model model) throws JsonProcessingException {
            DataTableRequest<CallTableWrapper> dataTableInRQ = new DataTableRequest<>(request);
            DataTableResults<CallTableWrapper> dataTableResults = this.stockCallService.getCallsTable(dataTableInRQ);
            return ResponseEntity.ok(new Gson().toJson(dataTableResults));
      }
}
