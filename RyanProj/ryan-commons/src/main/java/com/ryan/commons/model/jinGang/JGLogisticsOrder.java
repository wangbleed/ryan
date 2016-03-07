package com.ryan.commons.model.jinGang;

import java.util.List;

/**
 * Created by bin on 2015/11/4.
 */
public class JGLogisticsOrder {
    private String mailNo; // 面单号

    private String txLogisticID; // 物流平台订单号，如果有，请提供

    private String mailType; // 面单类型

    private String orderStatus; // 面单状态

    private List<JGLogisticsStep> steps;

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<JGLogisticsStep> getSteps() {
        return steps;
    }

    public void setSteps(List<JGLogisticsStep> steps) {
        this.steps = steps;
    }
}
