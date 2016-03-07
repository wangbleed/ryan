package com.ryan.commons.model.jinGang;

import com.ryan.commons.entity.BaseEntity;

import java.io.Serializable;

/** 金刚系统的返回值model
 * Created by admin on 2015/10/22.
 */
public class JGBaseModel extends BaseEntity implements Serializable {
    private String code;
    private String error;
    private String result;
    private String count;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
