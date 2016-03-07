package com.ryan.commons.model;

import com.ryan.commons.entity.BaseEntity;

/**
 * Created by bin on 2015/11/12.
 */
public class PushParams extends BaseEntity {
    private Byte type;
    private String filter;
    private String message;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
