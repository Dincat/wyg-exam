package com.wyg.common.core.utils;


import com.wyg.common.core.utils.sign.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;

public class AESUtils {

    //加密
    public static byte[] AES_cbc_encrypt(byte[] srcData,byte[] key,String key2) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(key2.getBytes(StandardCharsets.UTF_8)));
        byte[] encData = cipher.doFinal(srcData);
        return encData;
    }


    //解密
    public static byte[] AES_cbc_decrypt(byte[] encData,byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decbbdt = cipher.doFinal(encData);
        return decbbdt;
    }

    public static void main(String[] args) throws Exception{
        String str = "amHL1oRePgySnHXfulbnHhVYbXv+Ru6SfBiFouCrNh1d/9njqi1ZtOkN1Q+MHnOPO1w8vgDw2Ok7qUXwbw24tB2Qqsd/YYbX4A54o54vivqukxctECWDv2jSlvsCE9rE2+tV+kM/HQ9maPHwAljpz/MIpi4rS1SMCxYS2NXZUbmZo2wuJk3hSQS3DoBmcnwg7g32BUgsfeMJxEOagvw1OuFMww7cc66QcfhkRspbLjR04MlGteC9ege8G3GVzxdxPoUtZSzGH3h+PIfYAA/6cpmXnz7qRSpN9AYZgODfQp8lMLlDAC+OSPVoachDIWSInqfN4GNMdGAdCLoVLpseB/gAGdvtsny/EFwqav4nJFVnN+uyahfBh5yvAxlbYTSUQaS5Z78NK50QLXegYYFJJOi/MqlAGHVUe600FQT9tVMzwEp7z5SnNJD0yjzMhBbJTilCDQynvpJR0AQTe4S/p9aff0W5mHUYvlqyftEZ+pdb7iInRJMe7gO4LPI4+2y7SQlQmjsPE3K9RiVVBsQ5YXiGA79gC+zLjT6LVBaSgWbObvLLtZ/m/ZI7iOPLNiw48D0QOoUV76ReLkcZOLSxENA4ejK0OWkxTATVS/GQzL0khvWiSrR0W+67hPFJWxGI6K1uuyR4/oG+eTcgKkAaSqFg1BFxR35tXL03wICkdeoxPKgihJlZVMtSgyuGRGxXy5qEf6rmMi5DEuT7N1pSH4umQTLzqHUahixccA+DqGCN9iwlvwbwNfSZrPxNlNftmsyxVSQXi+TpbDgceMnhsUQyHwD9QBFPVO+iSILuU2gJeqauXqq9HJfdxxfe9T9tMKTu6Zyh+uSwkyWRrODr56E4LIBIBFkJGeLzoCU1QYAtzocVIrcFLSglTxZj6nSuUY/DAG6Yhu1ZLuA85ihYvZofBRpMbt49K063jqwc04iKso0d5vv6FfVDo8mf3r/sjCo2cSkoyml6reK3SDgUCeXnB3RdOs+sid+MVuGvsT6U/tnEdpe4kXgzhF9bvlGuzvJF0l9Uv32I8RzDuc/jRoro4B1nEdm9FHioHHB3PNw/fasnWWpVNjYzt6h3tKxu";

        byte[] decode = Base64.decode(str);

        String key = "sCVaAviWhNhbJo0FTvqhB27LbpLWKcVi";
        String StrMD5 = MD5Utils.MD5(key).toLowerCase();

        byte[] bytes = AES_cbc_decrypt(decode, StrMD5.getBytes(StandardCharsets.UTF_8));
        String s = new String(bytes, "UTF-8");
        System.out.println(s);
    }

}
