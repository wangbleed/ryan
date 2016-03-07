package com.ryan.commons.util;

import java.util.HashMap;
import java.util.Map;

/**
 * date: 12-11-23 上午9:47
 *
 * @author:clong
 */
public class AjaxResponseBodyUtils {
    public static Map<String, Object> getModel(boolean bool, String msg, Object data){
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("status", bool);
        model.put("msg", msg);
        model.put("data", data);

        return model;
    }


    public static Map<String, Object> getSuccessModel(String msg, Object data){
        return getModel(true, msg, data);
    }


    public static Map<String, Object> getErrorModel(String msg, Object data){
        return getModel(false, msg, data);
    }


    public static Map<String, Object> getErrorModel(String msg){
        return getErrorModel(msg, null);
    }

}
