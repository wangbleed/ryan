package com.ryan.commons.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: wangjia
 * Date: 13-7-23
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";

        if(!StringUtils.hasLength(source)) {
            //①如果source为空 返回null
            return null;
        }
        if(!source.contains(":")) {
            dateFormatPattern = "yyyy-MM-dd";
        }
        DateFormat df = new SimpleDateFormat(dateFormatPattern);
        try {
            //②转换成功
            return df.parse(source);
        } catch (ParseException e) {
            //③转化失败
            throw new IllegalArgumentException(String.format("类型转换失败，需要格式%s，但格式是[%s]", dateFormatPattern, source));
        }
    }
}
