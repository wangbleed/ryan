package com.ryan.commons.push;

import com.ryan.commons.entity.BaseEntity;

/**
 * Created by ryan on 15/11/4.
 */
public class PushFilter extends BaseEntity{

    private String courierCode;
    private String phone;

    public PushFilter() {
    }

    public PushFilter(String courierCode, String phone) {
        this.courierCode = courierCode;
        this.phone = phone;
    }

    public String getCourierCode() {
        return courierCode;
    }

    public void setCourierCode(String courierCode) {
        this.courierCode = courierCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
