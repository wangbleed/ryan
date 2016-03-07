package com.ryan.commons.model;

/**
 * Created by bin on 2015/11/10.
 */
public class SingleCallBeanRequest {
    private String callnumber;
    private String shownumber;
    private String waybillno;

    public String getCallnumber() {
        return callnumber;
    }

    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }

    public String getShownumber() {
        return shownumber;
    }

    public void setShownumber(String shownumber) {
        this.shownumber = shownumber;
    }

    public String getWaybillno() {
        return waybillno;
    }

    public void setWaybillno(String waybillno) {
        this.waybillno = waybillno;
    }
}
