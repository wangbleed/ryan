package com.ryan.commons.model;

/**
 * Created by bin on 2015/11/10.
 */
public class AliTel {
    private String partner_code;
    private String caller_number;
    private String caller_show_number;
    private String called_code;
    private String called_type;
    private String called_show_number;
    private String params;

    public String getPartner_code() {
        return partner_code;
    }

    public void setPartner_code(String partner_code) {
        this.partner_code = partner_code;
    }

    public String getCaller_number() {
        return caller_number;
    }

    public void setCaller_number(String caller_number) {
        this.caller_number = caller_number;
    }

    public String getCaller_show_number() {
        return caller_show_number;
    }

    public void setCaller_show_number(String caller_show_number) {
        this.caller_show_number = caller_show_number;
    }

    public String getCalled_code() {
        return called_code;
    }

    public void setCalled_code(String called_code) {
        this.called_code = called_code;
    }

    public String getCalled_type() {
        return called_type;
    }

    public void setCalled_type(String called_type) {
        this.called_type = called_type;
    }

    public String getCalled_show_number() {
        return called_show_number;
    }

    public void setCalled_show_number(String called_show_number) {
        this.called_show_number = called_show_number;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
