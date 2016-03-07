package com.ryan.commons.model;

/**
 * 根据号码双呼:用于接收从客户端收到数据
 * Created by bin on 2015/11/10.
 */
public class DouCallBeanRequest {
    private String PartnerCode;
    private String CallerNum;
    private String CallerShowNum;
    private String CalledNum;
    private String CalledShowNum;
    private String Waybillno;
    private String userId;
    private String Params;
    private String receiverName;

    public String getPartnerCode() {
        return PartnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        PartnerCode = partnerCode;
    }

    public String getCallerNum() {
        return CallerNum;
    }

    public void setCallerNum(String callerNum) {
        CallerNum = callerNum;
    }

    public String getCallerShowNum() {
        return CallerShowNum;
    }

    public void setCallerShowNum(String callerShowNum) {
        CallerShowNum = callerShowNum;
    }

    public String getCalledNum() {
        return CalledNum;
    }

    public void setCalledNum(String calledNum) {
        CalledNum = calledNum;
    }

    public String getCalledShowNum() {
        return CalledShowNum;
    }

    public void setCalledShowNum(String calledShowNum) {
        CalledShowNum = calledShowNum;
    }

    public String getWaybillno() {
        return Waybillno;
    }

    public void setWaybillno(String waybillno) {
        Waybillno = waybillno;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParams() {
        return Params;
    }

    public void setParams(String params) {
        Params = params;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
