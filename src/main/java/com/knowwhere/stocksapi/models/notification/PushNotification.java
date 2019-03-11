package com.knowwhere.stocksapi.models.notification;

import java.util.Objects;

public class PushNotification {
      private String to, sound, body, title;

      public PushNotification(String to, String sound, String body, String title) {
            this.to = to;
            this.sound = sound;
            this.body = body;
            this.title = title;
      }

      public String getTo() {
            return to;
      }

      public void setTo(String to) {
            this.to = to;
      }

      public String getSound() {
            return sound;
      }

      public void setSound(String sound) {
            this.sound = sound;
      }

      public String getBody() {
            return body;
      }

      public void setBody(String body) {
            this.body = body;
      }

      public String getTitle() {
            return title;
      }

      public void setTitle(String title) {
            this.title = title;
      }

      @Override
      public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PushNotification that = (PushNotification) o;
            return Objects.equals(to, that.to) &&
                    Objects.equals(sound, that.sound) &&
                    Objects.equals(body, that.body) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(body, that.body);
      }

      @Override
      public int hashCode() {
            return Objects.hash(to, sound, body, title, body);
      }

      @Override
      public String toString() {
            return "PushNotification{" +
                    "to='" + to + '\'' +
                    ", sound='" + sound + '\'' +
                    ", body='" + body + '\'' +
                    ", title='" + title + '\'' +
                    ", body='" + body + '\'' +
                    '}';
      }
}
