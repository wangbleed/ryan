package com.ryan.commons.model.jinGang;

/**
 * Created by bin on 2015/11/10.
 */
public class JGUpdateInfoResult {
    private String txLogisticID;
    private String logisticProviderID;
    private String success;

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

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
