package com.knowwhere.stocksapi.services;

import com.google.gson.Gson;
import com.knowwhere.stocksapi.controllers.CommodityController;
import com.knowwhere.stocksapi.models.*;
import com.knowwhere.stocksapi.models.notification.PushNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
      @Autowired
      private UsersService usersService;

      public void pushNotificationToAll(String title, String message) {
            try {
                  HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://exp.host/--/api/v2/push/send").openConnection();

                  // Setting the headers
                  httpURLConnection.setRequestMethod("POST");

                  httpURLConnection.setRequestProperty("Host", "expo.host");
                  httpURLConnection.setRequestProperty("Content-Type", "application/json");
                  httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
                  httpURLConnection.setRequestProperty("Accept", "application/json");

                  httpURLConnection.setDoOutput(true);
                  httpURLConnection.setDoInput(true);


                  List<PushNotification> tokens = new ArrayList<>();
                  for(Users users : this.usersService.getAll()) {
                        if(users.getNotificationToken() != null || !users.getNotificationToken().equals("")) {
                              tokens.add(new PushNotification(users.getNotificationToken(), "default", message, title));
                        }
                  }

                  String payload = new Gson().toJson(tokens);
                  System.out.println("payload = " + payload);

                  httpURLConnection.getOutputStream().write(payload.getBytes());

                  String response = "", temp;
                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                  while((temp = bufferedReader.readLine()) != null)
                        response += temp;

                  System.out.println("response = " + response);
            } catch (Exception e) {
                  e.printStackTrace();
                  return;
            }
      }
}
