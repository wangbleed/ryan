package com.ryan.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2015/5/27.
 */
public class BuildParamMap {
    private static final Logger logger = LoggerFactory.getLogger(BuildParamMap.class);

    /**
     * 利用 java 反射获取对象相关数值组成map
     * @param obj
     * @param convertTime 是否将date类型转化为 "yyyy-MM-dd HH:mm:ss" 格式
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> exec(Object obj, boolean convertTime){
        if(obj == null){
           return null;
        }
        Map<String, Object> result = new HashMap<String, Object>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);
            String name = field.getName();

            Object value = null;
            try {
                if(convertTime && (field.getType() == Date.class)){
                    Date date = (Date)field.get(obj);
                    value = DateUtil.toSeconds(date);
                }else{
                    value = field.get(obj);
                }
            } catch (IllegalAccessException e) {
                logger.error("build request param error, class name is {}, attribute name is {}", obj.getClass().getName(), name);
            }
            if(value instanceof String){
                value = ((String) value).trim();
            }
            if(!StringUtils.isEmpty(value)) {
                result.put(name, value);
            }
        }

        return result;
    }



    public static void main(String[] args) {
        Tesst tesst = new Tesst(new Date(), "sdfhd");
        Map<String, Object> result = exec(tesst, true);
        Map<String, Object> resuldgft = exec(tesst, false);
        System.out.println(result.size());
    }

    public static class Tesst{
        private String name;
        private Date date;

        public Tesst(Date date, String name) {
            this.date = date;
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
