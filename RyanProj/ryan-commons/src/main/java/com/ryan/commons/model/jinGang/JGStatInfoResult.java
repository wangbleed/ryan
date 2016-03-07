package com.ryan.commons.model.jinGang;

import java.io.Serializable;

/**
 * Created by bin on 2015/11/9.
 */
public class JGStatInfoResult implements Serializable {
    private String sendNo;
    private String collectNo;
    private String income;

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getCollectNo() {
        return collectNo;
    }

    public void setCollectNo(String collectNo) {
        this.collectNo = collectNo;
    }
}
