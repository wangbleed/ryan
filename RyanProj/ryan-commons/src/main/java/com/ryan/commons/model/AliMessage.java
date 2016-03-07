package com.ryan.commons.model;

/**
 * Created by bin on 2015/11/10.
 */
public class AliMessage {
    private String partner_code;
    private String receiver_code;
    private String receiver_type;
    private String sms_code;
    private String params;

    public String getPartner_code() {
        return partner_code;
    }

    public void setPartner_code(String partner_code) {
        this.partner_code = partner_code;
    }

    public String getReceiver_code() {
        return receiver_code;
    }

    public void setReceiver_code(String receiver_code) {
        this.receiver_code = receiver_code;
    }

    public String getReceiver_type() {
        return receiver_type;
    }

    public void setReceiver_type(String receiver_type) {
        this.receiver_type = receiver_type;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
