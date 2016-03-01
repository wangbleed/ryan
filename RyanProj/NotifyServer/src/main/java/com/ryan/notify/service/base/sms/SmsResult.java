package com.ryan.notify.service.base.sms;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by ryan on 15/4/23.
 * 短信接口返回集
 */
@XStreamAlias("sms")
public class SmsResult {

    @XStreamAlias("id")
    private String id;

    @XStreamAlias("accept")
    private String accept;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
