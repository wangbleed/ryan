package com.ryan.commons.util.security;

import org.apache.commons.codec.digest.DigestUtils;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Created by ryan on 15/6/28.
 * 基础加密组件
 */
public abstract class Coder {

    public static final String KEY_MD5 = "MD5";
    public static final String KEY_SHA = "SHA";
    public static final String UTF_8 = "UTF-8";

    public static final String KEY_MAC = "HmacMD5";

    /**
     * base64加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] content) throws Exception{
        return Base64.getEncoder().encodeToString(content);
    }

    /**
     * base64减密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.getDecoder().decode(key);
    }

    /**
     * 私有的加密
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] content, String key) throws Exception {
        MessageDigest md = MessageDigest.getInstance(key);
        return md.digest(content);
    }

    /**
     * MD5加密
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(String content) throws Exception {
        return encrypt(content.getBytes(UTF_8), KEY_MD5);
    }

    /**
     * SHA加密
     * @param content
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(String content) throws Exception {
        return encrypt(content.getBytes(UTF_8), KEY_SHA);
    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

    /**
     * 16进制编码
     * @param content
     * @return
     * @throws Exception
     */
    public static String bytes2hex(byte[] content) throws Exception {
        StringBuilder hex = new StringBuilder();
        for(int i=0;i<=content.length;i++){
            byte b = content[i];
            boolean negative = false;
            if(b < 0)
                negative = true;
            int inte = Math.abs(b);
            if(negative)
                inte = inte | 0x80;
            String temp = Integer.toHexString(inte & 0xFF);
            if(temp.length() == 1){
                hex.append("0");
            }
            hex.append(temp.toLowerCase());
        }
        return hex.toString();
    }

    /**
     * 16进制解码
     * @param hex
     * @return
     * @throws Exception
     */
    public static byte[] hex2bytes(String hex) throws Exception {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i=0;i<hex.length();i=i+2){
            String subStr = hex.substring(i, i+2);
            boolean negative = false;
            int inte = Integer.parseInt(subStr, 16);
            if(inte > 127)
                negative = true;
            if( inte == 128)
                inte = -128;
            else if(negative)
                inte = 0 - (inte & 0x7F);
            byte b = (byte)inte;
            bytes[i/2] = b;
        }
        return bytes;
    }

    public static String md5Encode(String source) {
        if(source == null) return null;
        try {
            String res = new String(source);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(res.getBytes("UTF-8"));
            System.out.println("^^^^^^^^^^^^^^^^DDDDDDDDDDDD:"+Charset.defaultCharset().displayName());
            StringBuffer stringBuffer = new StringBuffer(bytes.length * 2);
            for (int i = 0; i < bytes.length; i++) {
                if ((bytes[i] & 0xff) < 0x10) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Long.toString(bytes[i] & 0xff, 16));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String encodeToUrlString(String str){
        String rev = "";
        try{
            str = java.net.URLEncoder.encode(str, "UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bytesMd5 = DigestUtils.md5(str);
        byte[] byteBase64 = Base64.getEncoder().encode(bytesMd5);
        rev = new String(byteBase64);
        return rev;
    }
}
