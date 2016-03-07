package com.ryan.commons.model.jinGang;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bin on 2015/11/10.
 */
public class JGMessageReq implements Serializable {
    private String message;
    private List list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
