package com.wyg.wechat.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACUtils {
    //HMAC-SHA256 16
    public static String HMAC(String message, String secret) throws Exception{
        //String secret=wxConfig.getPayKey();
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("utf-8"), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        byte[] hash = sha256_HMAC.doFinal(message.getBytes("utf-8"));

        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < hash.length; i++) {
            temp = Integer.toHexString(hash[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

}
