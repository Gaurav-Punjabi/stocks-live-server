package com.knowwhere.stocksapi.models;

import java.util.Objects;

public class Notification {
      private String title;
      private String content;

      public Notification(String title, String content) {
            this.title = title;
            this.content = content;
      }

      public String getTitle() {
            return title;
      }

      public String getContent() {
            return content;
      }

      @Override
      public String toString() {
            return "Notification{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Notification that = (Notification) o;
            return Objects.equals(title, that.title) &&
                    Objects.equals(content, that.content);
      }

      @Override
      public int hashCode() {
            return Objects.hash(title, content);
      }
}
