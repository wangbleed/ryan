package com.ryan.commons.entity;

import com.ryan.commons.enums.RespEnum;

import java.util.List;

/**
 *  @author Ryan
 */
public class RespMessage<T> {

    public enum Status {
        success, error
    }

    private Status status;
    private String message;
    private int code;
    private List<T> list;
    private T t;

    public RespMessage() {
    }

    public RespMessage(Status status) {
        this.status = status;
    }

    public RespMessage(Status status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public RespMessage(Status status, RespEnum respEnum){
        this.status = status;
        this.message = respEnum.getMsg();
        this.code = respEnum.getCode();
    }

    public RespMessage(Status status, String message, int code, List<T> list, T t) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.list = list;
        this.t = t;
    }

    public RespMessage(Status status, RespEnum respEnum, List<T> list, T t){
        this.status = status;
        this.message = respEnum.getMsg();
        this.code = respEnum.getCode();
        this.list = list;
        this.t = t;
    }

    public Status getStatus() {
        return status;
    }

    public RespMessage setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RespMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public RespMessage setCode(int code) {
        this.code = code;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public RespMessage setList(List<T> list) {
        this.list = list;
        return this;
    }

    public T getT() {
        return t;
    }

    public RespMessage setT(T t) {
        this.t = t;
        return this;
    }
}
