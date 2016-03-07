package com.ryan.commons.model.jinGang;

import java.io.Serializable;

/**
 * 有单取件参数 更新面单号
 * Created by bin on 2015/10/30.
 */
public class JGUpdateInfo implements Serializable{
    private String txLogisticID;        //物流平台的物流号
    private String mainlNo;             //vip客户标识(客户编号)
    private String clientId;            //面单号，在没有确定的情况下可能为空，如取消订单
    private String infoType;            //通知类型
    private String infoContent;         //通知内容 在infoType为INSTRUCTION时： UPDATE ：更新面单号 WITHDRAW：取消订单
    private String remark;              //备注

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getMainlNo() {
        return mainlNo;
    }

    public void setMainlNo(String mainlNo) {
        this.mainlNo = mainlNo;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
