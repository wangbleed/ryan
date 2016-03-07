package com.ryan.commons.model.jinGang;

import java.io.Serializable;

/**
 * Created by bin on 2015/10/30.
 */
public class JGStatInfoReq implements Serializable {
    private String orgCode;
    private String courierCode;
    private String monthDay;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getCourierCode() {
        return courierCode;
    }

    public void setCourierCode(String courierCode) {
        this.courierCode = courierCode;
    }

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }
}
