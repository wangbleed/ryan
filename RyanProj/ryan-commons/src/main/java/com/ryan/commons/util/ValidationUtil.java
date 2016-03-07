package com.ryan.commons.util;

import com.ryan.commons.constant.Global;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2015/6/17.
 */
public class ValidationUtil {
    /**
     * 验证mCode 是否正确
     * @param mCode
     * @return
     */
    public static boolean checkMCode(String mCode){
        Pattern pattern = Pattern.compile(Global.MCODE_PATTERN);
        Matcher matcher = pattern.matcher(mCode);
        return matcher.matches();
    }

    /**
     * 验证是否是手机号
     * @param password
     * @return
     */
    public static boolean isPwd(String password){
        Pattern p = Pattern.compile(Global.REGEX_PASSWORD);
        Matcher m = p.matcher(password);
      return m.matches();
    }

    /**
     * 验证是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNo(String mobiles){
        Pattern p = Pattern.compile(Global.REGEX_PHONE);
        Matcher m = p.matcher(mobiles);
      return m.matches();
    }

    /**
     * 验证寄件人姓名是否正确
     * @param name
     * @return
     */
    public static boolean isSenderName(String name){
        Pattern p = Pattern.compile(Global.REGEX_SENDER_NAME);
        Matcher m = p.matcher(name);
      return m.matches();
    }

    public static void main(String[] args) {
         double d = 4.0d;
          byte b = (byte) d;
          System.out.println(checkMCode("0090"));
    }
}
