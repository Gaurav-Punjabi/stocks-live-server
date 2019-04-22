package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.exceptions.FieldNotFoundException;
import com.knowwhere.stocksapi.exceptions.NonUniqueFieldException;
import com.knowwhere.stocksapi.models.Users;
import com.knowwhere.stocksapi.models.datatables.DataTableRequest;
import com.knowwhere.stocksapi.models.datatables.DataTableResults;
import com.knowwhere.stocksapi.models.users.UsersTableWrapper;
import com.knowwhere.stocksapi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {
      @Autowired
      private UsersRepository usersRepository;
      @Autowired
      private HashService hashService;
      @Autowired
      private JdbcTemplate jdbcTemplate;

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
      public Users store(String name,
                         String email,
                         String password,
                         String phoneNumber,
                         boolean isAdmin) throws IllegalArgumentException, NonUniqueFieldException {
            // Validating the input fields
            if(name == null) {
                  throw new IllegalArgumentException("Please provide a name");
            }
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

                  Users admin = new Users(name, email, hashedPassword, phoneNumber, isAdmin);
                  return this.usersRepository.save(admin);
            } catch (NoSuchAlgorithmException nsae) {
                  return null;
            }
      }

      /**
       * A method to edit a user into the database
       * @param email Email ID of the user
       * @param phoneNumber Phone number of the user (Must be 10 digits long)
       * @return Object of the User that has been inserted
       * @throws IllegalArgumentException If any of the argument is invalid or null
       * @throws NonUniqueFieldException If any of the given argument is not unique in the database
       */
      public Users update(int id,
                          String name,
                          String email,
                          String phoneNumber) throws IllegalArgumentException, NonUniqueFieldException, FieldNotFoundException {
            // Validating the input fields
            if(name == null) {
                  throw new IllegalArgumentException("Please provide a name");
            }
            if(email == null) {
                  throw new IllegalArgumentException("Please provide a email id.");
            } else if(phoneNumber == null || !(phoneNumber.matches("^\\d{10}$"))) {
                  throw new IllegalArgumentException("Please provide a valid phoneNumber");
            } else if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-\\+]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                  throw new IllegalArgumentException("Not a valid email");
            } else if(phoneNumber.length() != 10) {
                  throw new IllegalArgumentException("Phone Number is not valid");
            } else if(this.usersRepository.findByEmail(email).isPresent() && this.usersRepository.findByEmail(email).orElse(null).getId() != id) {
                  throw new NonUniqueFieldException("Given email already exists");
            } else if(this.usersRepository.findByPhoneNumber(phoneNumber).isPresent() && this.usersRepository.findByPhoneNumber(phoneNumber).orElse(null).getId() != id) {
                  throw new NonUniqueFieldException("Given phone number already exists");
            }

            Users user = this.getById(id);
            user.setName(name);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);

            this.usersRepository.save(user);

            return user;
      }

      public void destroy(int id) throws FieldNotFoundException {
            Users user = this.getById(id);

            user.setDeleted(true);
            this.usersRepository.save(user);
      }

      public Users registerAdmin(String name,
                                 String email,
                                 String password,
                                 String phoneNumber) throws IllegalArgumentException, NonUniqueFieldException {
            return this.store(name, email, password, phoneNumber, true);
      }

      public Users registerUser(String name,
                                String email,
                                String password,
                                String phoneNumber) throws IllegalArgumentException, NonUniqueFieldException {
            return this.store(name, email, password, phoneNumber, false);
      }

      public void pushToken(String email, String token) {
            Users users = this.usersRepository.findByEmail(email).orElse(null);
            if(users != null) {
                  users.setNotificationToken(token);
                  this.usersRepository.save(users);
            }
      }

      public List<Users> getAll() {
            return this.usersRepository.findAll();
      }

      public Users getById(int id) throws FieldNotFoundException {
            Users user = this.usersRepository.findById(id).orElse(null);
            if(user != null)
                  return user;
            throw new FieldNotFoundException("No user with id " + id + " found.");
      }


      public List<UsersTableWrapper> getUsers(final int start,
                                              final int length,
                                              final String columnName,
                                              final String sortDirection,
                                              final String search) {
            String query = String.format("SELECT * FROM Users WHERE Users.deleted = 0 and (Users.name like' %%%s%%' OR Users.email like '%%%s%%' OR Users.phone_number like '%%%s%%') ORDER BY %s %s LIMIT %s, %s", search, search, search, columnName, sortDirection, start, length);
            System.out.println("query = " + query);
            List<Map<String, Object>> result = this.jdbcTemplate.queryForList(query);
            List<UsersTableWrapper> usersTableWrappers = this.getUsersFromResult(result);
            return usersTableWrappers;
      }

      private List<UsersTableWrapper> getUsersFromResult(List<Map<String, Object>> result) {
            List<UsersTableWrapper> categoryTableWrappers = new ArrayList<>();
            for (Map<String, Object> row : result) {
                  int id = (Integer) row.get("id");
                  String name = (String) row.get("name");
                  String email = (String) row.get("email");
                  String phoneNumber = (String) row.get("phone_number");
                  categoryTableWrappers.add(new UsersTableWrapper(id, name, email, phoneNumber));
            }

            return categoryTableWrappers;
      }

      public DataTableResults<UsersTableWrapper> getUsersTable(DataTableRequest<UsersTableWrapper> dataTableRequest) {
            String columnName = dataTableRequest.getOrder().getData();
            String direction = dataTableRequest.getOrder().getSortDir();
            switch(columnName) {
                  case "name" :
                        columnName = "name";
                        break;

                  case "phoneNumber" :
                        columnName = "phone_number";
                        break;

                  case "email" :
                        columnName = "email";
                        break;

                  default:
                        break;
            }

            DataTableResults<UsersTableWrapper> dataTableResults = new DataTableResults<>();
            List<UsersTableWrapper> categoryTableWrappers = this.getUsers(dataTableRequest.getStart(), dataTableRequest.getLength(), columnName, direction, dataTableRequest.getSearch());

            dataTableResults.setDraw(dataTableRequest.getDraw());
            dataTableResults.setListOfDataObjects(categoryTableWrappers);
            dataTableResults.setRecordsFiltered(categoryTableWrappers.size() + "");
            dataTableResults.setRecordsTotal(this.getAll().size() + "");

            System.out.println("this.getAll().size() = " + this.getAll().size());

            return dataTableResults;
      }
}
