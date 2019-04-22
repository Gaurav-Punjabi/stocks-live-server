package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.exceptions.FieldNotFoundException;
import com.knowwhere.stocksapi.models.CallType;
import com.knowwhere.stocksapi.models.StockCall;
import com.knowwhere.stocksapi.models.StockInfo;
import com.knowwhere.stocksapi.models.datatables.DataTableRequest;
import com.knowwhere.stocksapi.models.datatables.DataTableResults;
import com.knowwhere.stocksapi.models.stock_call.CallTableWrapper;
import com.knowwhere.stocksapi.models.users.UsersTableWrapper;
import com.knowwhere.stocksapi.repositories.StockCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockCallService {
      @Autowired
      private StockCallRepository stockCallRepository;
      @Autowired
      private StockInfoService stockInfoService;
      @Autowired
      private CallTypeService callTypeService;
      @Autowired
      private JdbcTemplate jdbcTemplate;

      public List<StockCall> getAllStockCalls() {
            return this.stockCallRepository.findAll();
      }

      public StockCall setCompleted(StockCall stockCall) {
            stockCall.setCompleted(true);
            return this.stockCallRepository.save(stockCall);
      }

      public List<StockCall> getAllByStockInfo(int id) {
            return this.stockCallRepository.getAllByStockInfo(id);
      }

      public StockCall add(final String name,
                           final String type,
                           final double currentPrice,
                           final double stopLoss,
                           final double target1,
                           final double target2,
                           final double target3) throws FieldNotFoundException {
            Optional<StockInfo> optionalStockInfo = this.stockInfoService.getByName(name);
            StockInfo stockInfo = null;
            if (!optionalStockInfo.isPresent()) {
                  stockInfo = this.stockInfoService.add(name, 1);
            } else {
                  stockInfo = optionalStockInfo.get();
            }
            Optional<CallType> optionalCallType = this.callTypeService.getByName(type);
            if (optionalCallType.isPresent()) {
                  CallType callType = optionalCallType.get();
                  System.out.println("callType = " + callType);
                  StockCall stockCall = new StockCall(stockInfo, callType, currentPrice, stopLoss, target1, target2, target3);
                  System.out.println("stockCall = " + stockCall);
                  stockCall.setCreatedAt(new Date());
                  System.out.println("stockCall = " + stockCall);
                  return this.stockCallRepository.save(stockCall);
            }
            return null;
      }

      public List<StockCall> getAllByCompleted(boolean completed) {
            return this.stockCallRepository.getAllByCompleted(completed);
      }

      public List<CallTableWrapper> getUsers(final int start,
                                              final int length,
                                              final String columnName,
                                              final String sortDirection,
                                              final String search) {
            String query = String.format("select StockCall.id as id," +
                          "CallType.name as type, \n" +
                          "StockInfo.name as name,\n" +
                          "StockCall.price as price,\n" +
                          "StockCall.stopLoss as stopLoss,\n" +
                          "StockCall.target1 as target1,\n" +
                          "StockCall.target2 as target2,\n" +
                          "StockCall.target3 as target3,\n" +
                          "StockCall.createdAt as placedOn\n" +
                    "from StockCall\n" +
                    "inner join StockInfo on StockInfo.id = StockCall.StockId\n" +
                    "inner join CallType on CallType.id = StockCall.TypeId\n" +
                    "where StockCall.completed = 0 and (" +
                        "StockInfo.name like '%%%s%%' or " +
                        "CallType.name like '%%%s%%' or " +
                        "StockCall.price like '%%%s%%' or " +
                        "StockCall.stopLoss like '%%%s%%' or " +
                        "StockCall.target1 like '%%%s%%' or " +
                        "StockCall.target2 like '%%%s%%' or " +
                        "StockCall.target3 like '%%%s%%'" +
                    ")", search, search, search, search, search, search, search, columnName, sortDirection, start, length);

            System.out.println("query = " + query);
            List<Map<String, Object>> result = this.jdbcTemplate.queryForList(query);
            List<CallTableWrapper> callsTableWrapper = this.getCallsFromResult(result);
            return callsTableWrapper;
      }

      private List<CallTableWrapper> getCallsFromResult(List<Map<String, Object>> result) {
            List<CallTableWrapper> callTableWrapper = new ArrayList<>();
            for (Map<String, Object> row : result) {
                  int id = (Integer) row.get("id");
                  String name = (String) row.get("name");
                  String type = (String) row.get("type");
                  double price = (Double) row.get("price");
                  double stopLoss = (Double) row.get("stopLoss");
                  double target1 = (Double) row.get("target1");
                  double target2 = (Double) row.get("target2");
                  double target3 = (Double) row.get("target3");
                  String placedOn = row.get("placedOn").toString();

                  callTableWrapper.add(new CallTableWrapper(id, type, name, placedOn, price, stopLoss, target1, target2, target3));
            }

            return callTableWrapper;
      }

      public DataTableResults<CallTableWrapper> getCallsTable(DataTableRequest<CallTableWrapper> dataTableRequest) {
            String columnName = dataTableRequest.getOrder().getData();
            String direction = dataTableRequest.getOrder().getSortDir();
//            switch(columnName) {
//                  case "name" :
//                        columnName = "StockCall.name";
//                        break;
//
//                  case "type" :
//                        columnName = "CallType.name";
//                        break;
//
//                  case "email" :
//                        columnName = "email";
//                        break;
//
//                  default:
//                        break;
//            }

            DataTableResults<CallTableWrapper> dataTableResults = new DataTableResults<>();
            List<CallTableWrapper> categoryTableWrappers = this.getUsers(dataTableRequest.getStart(), dataTableRequest.getLength(), columnName, direction, dataTableRequest.getSearch());

            dataTableResults.setDraw(dataTableRequest.getDraw());
            dataTableResults.setListOfDataObjects(categoryTableWrappers);
            dataTableResults.setRecordsFiltered(categoryTableWrappers.size() + "");
            dataTableResults.setRecordsTotal(this.getAllByCompleted(false).size() + "");

            System.out.println("this.getAll().size() = " + this.getAllByCompleted(false).size());

            return dataTableResults;
      }
}
