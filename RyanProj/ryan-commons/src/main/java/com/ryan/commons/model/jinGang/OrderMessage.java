package com.ryan.commons.model.jinGang;

/**
 * 配合 电子面单（返回值）
 * Created by bin on 2015/11/10.
 */
public class  OrderMessage{
    private String mailNo;
    private String clientID;
    private String txLogisticID;
    private String bigPen;

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getBigPen() {
        return bigPen;
    }

    public void setBigPen(String bigPen) {
        this.bigPen = bigPen;
    }
}
