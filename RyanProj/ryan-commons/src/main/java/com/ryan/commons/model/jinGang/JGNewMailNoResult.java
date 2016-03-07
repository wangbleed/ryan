package com.ryan.commons.model.jinGang;

import java.io.Serializable;

/**
 * 电子面单
 * Created by bin on 2015/11/9.
 */
public class JGNewMailNoResult implements Serializable {
    private String logisticProviderID;
    private String success;


    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }


}
