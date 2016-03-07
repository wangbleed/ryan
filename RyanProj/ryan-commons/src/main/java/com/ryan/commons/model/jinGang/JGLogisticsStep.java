package com.ryan.commons.model.jinGang;

import java.util.Date;

/**
 * Created by bin on 2015/11/4.
 */
public class JGLogisticsStep {
    private String name;				// 操作员
    private Boolean status;				// 状态
    private String acceptAddress;		// 操作地址
    private String remark;				// 备注
    private Date acceptTime; 			// 操作时间

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getAcceptAddress() {
        return acceptAddress;
    }
    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getAcceptTime() {
        return acceptTime;
    }
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }
}
