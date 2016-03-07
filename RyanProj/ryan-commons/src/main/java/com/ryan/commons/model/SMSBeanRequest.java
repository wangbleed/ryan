package com.ryan.commons.model;

/**
 * Created by bin on 2015/11/10.
 */
public class SMSBeanRequest {
    private String ReceiverNum;
    private String displayname;
    private String mobile;
    private String receiverName;
    private String waybillno;
    private String userId;
    private String smsDemoNo;
    private String address;

    public String getReceiverNum() {
        return ReceiverNum;
    }

    public void setReceiverNum(String receiverNum) {
        ReceiverNum = receiverNum;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getWaybillno() {
        return waybillno;
    }

    public void setWaybillno(String waybillno) {
        this.waybillno = waybillno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSmsDemoNo() {
        return smsDemoNo;
    }

    public void setSmsDemoNo(String smsDemoNo) {
        this.smsDemoNo = smsDemoNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
