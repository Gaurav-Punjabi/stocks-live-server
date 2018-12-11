package com.knowwhere.stocksapi.services;

import com.knowwhere.stocksapi.models.Commodity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WebScrapperService {
      private static final String TABLE_REGEX = "<html.+?>.*<table.*fullMcxPriceTable.*?>(.*?)<\\/table>.*?<\\/html>";
      private static final String COMMODITY_REGEX = "<tr.*?> *<td.*?>([\\w\\d]*?)<\\/td> *<td.*?>([\\w\\d\\-\\.]*?)<\\/td> *<td.*?>([\\w\\d\\-\\.]*?)<\\/td> *<td.*?>([\\w\\d\\-\\.]*?)<\\/td> *<td.*?>([\\w\\d\\-\\.]*?)<\\/td> *<td.*?>([\\w\\d\\-\\.]*?)<\\/td> *<td.*?>([\\w\\d\\-\\.]*?)<\\/td> *<td.*?>([\\w\\d\\-\\.\\:]*?)<\\/td> *";
      private static final String COMMODITY_SOURCE_URL = "http://market.mcxdata.in/";

      private String getSourceCode(final String address) {
            try {
                  URL url = new URL(address);
                  HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();

                  String sourceCode = "";
                  String temp;

                  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));

                  while ((temp = bufferedReader.readLine()) != null)
                        sourceCode += temp;
                  return sourceCode;
            } catch (MalformedURLException malformedURLException) {
                  malformedURLException.printStackTrace();
                  return "";
            } catch (Exception e) {
                  e.printStackTrace();
                  return "";
            }
      }

      private String refineSourceCode(final String sourceCode) {
            Matcher matcher = Pattern.compile(TABLE_REGEX).matcher(sourceCode.replaceAll("[\n\t ]+", " "));
            if (matcher.find())
                  return matcher.group(1);
            return "";
      }

      public List<Commodity> getCommodities() {
            String sourceCode = this.getSourceCode(COMMODITY_SOURCE_URL);
            List<Commodity> commodities = new ArrayList<>();
            String refinedString = refineSourceCode(sourceCode);
            Matcher matcher = Pattern.compile(COMMODITY_REGEX).matcher(refinedString);
            while (matcher.find()) {
                  String name = matcher.group(1);
                  Double price = Double.parseDouble(matcher.group(2));
//                  String change = matcher.group(3);
//                  String changeInPercentage = matcher.group(4);
//                  String open = matcher.group(5);
//                  String high = matcher.group(6);
//                  String low = matcher.group(7);
//                  String time = matcher.group(8);
                  commodities.add(new Commodity(name, price));
            }
            return commodities;
      }
}
