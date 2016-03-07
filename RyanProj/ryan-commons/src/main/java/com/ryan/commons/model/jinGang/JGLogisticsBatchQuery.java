package com.ryan.commons.model.jinGang;

import java.util.List;

/**
 * Created by bin on 2015/11/4.
 */
public class JGLogisticsBatchQuery {
    private String logisticProviderID; // 物流公司编号

    private List<JGLogisticsOrder> orders;

    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }

    public List<JGLogisticsOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<JGLogisticsOrder> orders) {
        this.orders = orders;
    }


}
