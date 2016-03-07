package com.ryan.commons.model.jinGang;

import com.ryan.commons.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by bin on 2015/11/10.
 */
public class JGBaseModel2 extends BaseEntity implements Serializable{
    private String status;
    private String message;
    private String list;
    private String object;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
