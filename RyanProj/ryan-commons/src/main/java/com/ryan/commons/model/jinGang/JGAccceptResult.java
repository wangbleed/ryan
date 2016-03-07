package com.ryan.commons.model.jinGang;

import java.io.Serializable;

/**
 * 订单反馈 （2.1接口 及2.2 接口）
 * Created by bin on 2015/11/9.
 */
public class JGAccceptResult implements Serializable{
    private String logisticProviderId;
    private String txLogisticID;
    private String success;
    private String reason;

    public String getLogisticProviderId() {
        return logisticProviderId;
    }

    public void setLogisticProviderId(String logisticProviderId) {
        this.logisticProviderId = logisticProviderId;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
