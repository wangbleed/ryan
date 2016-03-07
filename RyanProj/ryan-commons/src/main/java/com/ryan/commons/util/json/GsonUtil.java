package com.ryan.commons.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

/**
 * Created by admin on 2015/7/10.
 */
public class GsonUtil {

    private static Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()                         //支持Map的key为复杂对象的形式
            .setDateFormat("yyyy-MM-dd HH:mm:ss")                       //时间转化为特定格式
//            .setPrettyPrinting()                                      //对json结果格式化.
            .setVersion(1.0)                                            //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
            .create();

    public static Gson buildGson(){
        return gson;
    }

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T getBean(String json, Class<T> cls){
        return gson.fromJson(json, cls);
    }

    public static <T> T getBean(String json, Type type){
        return gson.fromJson(json, type);
    }

    public static <T> T getBean(JsonElement jsonElement, Type type){
        return gson.fromJson(jsonElement, type);
    }
}
