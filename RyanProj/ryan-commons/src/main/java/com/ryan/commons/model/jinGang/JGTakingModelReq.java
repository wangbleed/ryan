package com.ryan.commons.model.jinGang;

import com.ryan.commons.entity.BaseEntity;

import java.io.Serializable;

/**
 * 无单 取件 接口参数
 * Created by admin on 2015/10/26.
 */
public class JGTakingModelReq extends BaseEntity implements Serializable {
    private String waybillNo;                               //面单号
    private String desprov;                                 //目的省份
    private String desCity;                                 //目的城市
    private String desCountry;                              //区
    private String timeEffectiveCode;                       //时效
    private String weight;                                  //重量
    private String opcode;                                  //312 代表无单称重取件 311代表无单正常取件
    private String deviceType;                              //Andorid IOS
    private String desplace;                                //收件地址
    private String desOrgCode;                              //派件网点代码
    private String receiveTime;                             //接受时间
    private String oper_code;                               //当前登录用户 员工号
    private String userName;                                //员工工号
    private String mobile;                                  //当前登录手机号
    private String orgCode;                                 //当前登录人的 网点代码

    private String tokenId;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getDesprov() {
        return desprov;
    }

    public void setDesprov(String desprov) {
        this.desprov = desprov;
    }

    public String getDesCity() {
        return desCity;
    }

    public void setDesCity(String desCity) {
        this.desCity = desCity;
    }

    public String getDesCountry() {
        return desCountry;
    }

    public void setDesCountry(String desCountry) {
        this.desCountry = desCountry;
    }

    public String getTimeEffectiveCode() {
        return timeEffectiveCode;
    }

    public void setTimeEffectiveCode(String timeEffectiveCode) {
        this.timeEffectiveCode = timeEffectiveCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDesplace() {
        return desplace;
    }

    public void setDesplace(String desplace) {
        this.desplace = desplace;
    }

    public String getDesOrgCode() {
        return desOrgCode;
    }

    public void setDesOrgCode(String desOrgCode) {
        this.desOrgCode = desOrgCode;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getOper_code() {
        return oper_code;
    }

    public void setOper_code(String oper_code) {
        this.oper_code = oper_code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
