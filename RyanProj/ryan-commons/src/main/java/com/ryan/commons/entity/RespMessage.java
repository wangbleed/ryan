package com.ryan.commons.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yqq
 * Date: 13-12-19
 * Time: 下午1:40
 * To change this template use File | Settings | File Templates.
 */
public class RespMessage {

    public enum Status {
        success, error
    }

    private Status status;
    private String message;
    private List<?> list;
    private Object object;


    public RespMessage(Status status) {
        this.status = status;
    }

    public RespMessage(Status status, List<?> list) {
        this.status = status;
        this.list = list;
    }

    public RespMessage(Status status, Object object) {
        this.status = status;
        this.object = object;
    }


    public RespMessage(Status status, String message){
        this.status = status;
        this.message = message;
    }

    public RespMessage(){}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
