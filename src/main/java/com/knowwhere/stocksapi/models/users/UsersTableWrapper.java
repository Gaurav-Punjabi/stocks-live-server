package com.knowwhere.stocksapi.models.users;

import com.knowwhere.stocksapi.models.datatables.EditableTableWrapper;

public class UsersTableWrapper extends EditableTableWrapper  {
      private String name, email, phoneNumber;

      public UsersTableWrapper(int id, String name, String email, String phoneNumber) {
            super(id);

            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public String getPhoneNumber() {
            return phoneNumber;
      }

      public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
      }
}
