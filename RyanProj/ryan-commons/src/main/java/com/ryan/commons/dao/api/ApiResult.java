package com.ryan.commons.dao.api;

import com.ryan.commons.entity.BaseEntity;

import java.util.List;

/**
 * Created by ryan on 15/10/30.
 * RPC Api Result
 */
public class ApiResult<T extends BaseEntity> implements Cloneable{

    private int code;
    private String prompt;
    private T t;
    private List<T> lst;

    public ApiResult(){}

    public ApiResult(int code) {
        this.code = code;
    }

    public ApiResult(int code, String prompt) {
        this.code = code;
        this.prompt = prompt;
    }
    
    public int getCode() {
        return code;
    }

    public ApiResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getPrompt() {
        return prompt;
    }

    public ApiResult setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public T getT() {
        return t;
    }

    public ApiResult setT(T t) {
        this.t = t;
        return this;
    }

    public List<T> getLst() {
        return lst;
    }

    public ApiResult setLst(List<T> lst) {
        this.lst = lst;
        return this;
    }

    @Override
    public ApiResult clone() {
        try {
            return (ApiResult) super.clone();
        } catch (Exception e){
            return null;
        }
    }
}
