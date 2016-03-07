package com.ryan.commons.entity;

import com.ryan.commons.util.json.GsonUtil;
import org.apache.commons.lang3.builder.*;

import java.io.Serializable;

/**
 * Created by ryan on 15/7/14.
 */
public class BaseEntity implements Serializable {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public int compareTo(Object obj){
        return CompareToBuilder.reflectionCompare(this, obj);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String toJson(){
        return GsonUtil.toJson(this);
    }

    public static <T> T getBean(String json, Class<T> cls){
        return GsonUtil.getBean(json, cls);
    }
}
