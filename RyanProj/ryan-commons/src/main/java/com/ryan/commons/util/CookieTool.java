package com.ryan.commons.util;

import com.ryan.commons.constant.Global;
import com.ryan.commons.util.security.AES256Encryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieTool {
    private static transient final Logger logger = LoggerFactory.getLogger(CookieTool.class);

    public static void addAutoLoginCookie(HttpServletResponse response,HttpServletRequest request,String username,String password){
        try {
            String value = String.format("%s%s%s", username, Global.COOKIE_SEPARATOR, password);
            byte[] data = AES256Encryption.encrypt(value.getBytes(), Global.COOKIE_SECRET);

            String pwd = AES256Encryption.parseByte2HexStr(data);

            addCookie(response, request, Global.REMEMBER_ME_KEY, pwd, Global.LOGIN_COOIKE_MAX_AGE);
        } catch (Exception e) {
            logger.error("auto login entry error, error info is {}", e.getMessage());
        }
    }

    /**
     * 设置cookie（接口方法）
     * @param response
     * @param name  cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response,HttpServletRequest request,String name,String value,int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath(request.getContextPath());
        if(maxAge >= 0){
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie（接口方法）
     * @param request
     * @param name cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面（非接口方法）
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}