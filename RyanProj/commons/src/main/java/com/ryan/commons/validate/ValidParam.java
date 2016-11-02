package com.ryan.commons.validate;

import com.ryan.commons.entity.RespMessage;
import com.ryan.commons.enums.RespEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by xudongwang on 2016/6/22.
 */
public class ValidParam {

    public static RespMessage valid(Object... args){
        RespMessage message = new RespMessage(RespMessage.Status.error, RespEnum.C1002);
        if(null == args)
            return message;
        else{
            for(Object arg : args){
                if(null == arg) {
                    return message;
                }

                if( arg instanceof String && StringUtils.isEmpty(String.valueOf(arg))){
                    return message;
                } else if( arg instanceof Integer && NumberUtils.toLong(String.valueOf(arg)) < 0){
                    return message;
                } else if( arg instanceof Long && NumberUtils.toLong(String.valueOf(arg)) < 0){
                    return message;
                } else if( arg instanceof Double && NumberUtils.toDouble(String.valueOf(arg)) <= 0){
                    return message;
                }
            }
        }
        message = new RespMessage(RespMessage.Status.success, RespEnum.C1000);
        return message;
    }

}
