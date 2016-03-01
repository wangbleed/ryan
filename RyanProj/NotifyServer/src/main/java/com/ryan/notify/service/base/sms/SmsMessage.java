package com.ryan.notify.service.base.sms;

import com.ryan.commons.entity.BaseEntity;

import java.util.Date;

/**
 * Created by admin on 2015/10/20.
 */
public class SmsMessage extends BaseEntity {

    private static final long serialVersionUID = 197722173204732412L;

    private Date createTime = new Date();
    private Date updateTime = null;

    private Long id;
    private String content;             //   短信内容
    private String phone;               //   接收短信手机号码
    private String errorInfo;           //   错误信息
    private Byte priority;              //   短信优先级 1，2，3，4，5数字越大，等级越高
    private Byte status;                //   短信状态(1:发送中，2:已发送，3:发送失败)
    private Byte type;                  //   短信类型(0:绑定手机验证码)
    private String orgCode;             //   网点代码
    private String jobNo;               //   工号

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
