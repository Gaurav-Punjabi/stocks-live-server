package com.knowwhere.stocksapi.models.datatables;

public class ProductWrapper {
      private String manufacturer, category, subcategory, color, material, size;
      private int count;

      public ProductWrapper(String manufacturer,
                            String category,
                            String subcategory,
                            String color,
                            String material,
                            String size,
                            int count) {
            this.manufacturer = manufacturer;
            this.category = category;
            this.subcategory = subcategory;
            this.color = color;
            this.material = material;
            this.size = size;
            this.count = count;
      }

      public String getManufacturer() {
            return manufacturer;
      }

      public String getCategory() {
            return category;
      }

      public String getSubcategory() {
            return subcategory;
      }

      public String getColor() {
            return color;
      }

      public String getMaterial() {
            return material;
      }

      public int getCount() {
            return count;
      }

      public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
      }

      public void setCategory(String category) {
            this.category = category;
      }

      public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
      }

      public void setColor(String color) {
            this.color = color;
      }

      public void setMaterial(String material) {
            this.material = material;
      }

      public void setCount(int count) {
            this.count = count;
      }

      public String getSize() {
            return size;
      }

      public void setSize(String size) {
            this.size = size;
      }

      @Override
      public String toString() {
            return "ProductWrapper{" +
                    "manufacturer='" + manufacturer + '\'' +
                    ", category='" + category + '\'' +
                    ", subcategory='" + subcategory + '\'' +
                    ", color='" + color + '\'' +
                    ", material='" + material + '\'' +
                    ", count=" + count +
                    '}';
      }
}
