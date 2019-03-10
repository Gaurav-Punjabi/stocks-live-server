package com.knowwhere.stocksapi.services;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class HashService {
      public static String SHA1 = "SHA1";
      public String stringToSha1(String input) throws NoSuchAlgorithmException {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA1);
            byte[] result = messageDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                  sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
      }

}
