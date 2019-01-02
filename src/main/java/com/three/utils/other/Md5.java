package com.three.utils.other;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

     public static String MD5(String oldStr){
         byte [] oldBytes = oldStr.getBytes();
         MessageDigest messageDigest;
         try {
             messageDigest = MessageDigest.getInstance("MD5");
             byte [] newBytes = messageDigest.digest(oldBytes);
             BASE64Encoder encoder = new BASE64Encoder();
             String newStr = encoder.encode(newBytes);
             return newStr;

         } catch (NoSuchAlgorithmException e) {
             return  null;
         }
     }
}
