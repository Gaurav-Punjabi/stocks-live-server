package com.knowwhere.stocksapi.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
public class MessageService {
      public void sendMessage(String phoneNumber, String text) {
            String authkey = "265094Ax4iz2OyViR5c76a738";
            //Multiple mobiles numbers separated by comma
            String mobiles = "91" + phoneNumber;
            System.out.println("mobiles = " + mobiles);
            //Sender ID,While using route4 sender id should be 6 characters long.
            String senderId = "STOCKS";
            //Your message to send, Add URL encoding here.
            String message = text;
            //define route
            String route="route4";

            //Prepare Url
            URLConnection myURLConnection = null;
            URL myURL = null;
            BufferedReader reader = null;

            //encoding message
            String encoded_message= URLEncoder.encode(message);

            //Send SMS API
            String mainUrl="http://api.msg91.com/api/sendhttp.php?";

            //Prepare parameter string
            StringBuilder sbPostData= new StringBuilder(mainUrl);
            sbPostData.append("authkey="+authkey);
            sbPostData.append("&mobiles="+mobiles);
            sbPostData.append("&message="+encoded_message);
            sbPostData.append("&route="+route);
            sbPostData.append("&sender="+senderId);

            //final string
            mainUrl = sbPostData.toString();
            try {
                  //prepare connection
                  myURL = new URL(mainUrl);
                  myURLConnection = myURL.openConnection();
                  myURLConnection.connect();
                  reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                  //reading response
                  String response;
                  while ((response = reader.readLine()) != null)
                        //print response
                        System.out.println(response);

                  //finally close connection
                  reader.close();
            }
            catch (IOException e) {
                  e.printStackTrace();
            }
      }
}
