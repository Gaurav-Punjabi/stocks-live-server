package com.knowwhere.stocksapi.models.datatables;

public class EditableTableWrapper {
      private int id;
      private String edit, delete;

      public EditableTableWrapper(int id) {
            this.edit = "<button class=\"btn blue edit\" id=\"" + id + "\" data-target=\"#editModal\" data-toggle=\"modal\"><i class=\"fa fa-pencil\"></i></button>";
            this.delete = "<button class=\"btn red delete\" id=\"" + id + "\" data-target=\"#deleteModal\" data-toggle=\"modal\"><i class=\"fa fa-trash\"></i></button>";
      }

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getEdit() {
            return edit;
      }

      public void setEdit(String edit) {
            this.edit = edit;
      }

      public String getDelete() {
            return delete;
      }

      public void setDelete(String delete) {
            this.delete = delete;
      }
}
