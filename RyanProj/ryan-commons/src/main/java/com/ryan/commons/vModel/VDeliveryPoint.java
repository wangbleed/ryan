package com.ryan.commons.vModel;

import java.io.Serializable;

/**
 * Created by aaron_yu on 2015/5/27.
 * 用户view层
 */
public class VDeliveryPoint implements Serializable{
    private String provinceCode;        // 省
    private String cityCode;        // 市
    private String branchCode;        // 分公司
    private String terminalCode;        // 分部
    private String pointName;        // 自提点名称
    private String pointCode;        // 自提点code
    private String address; //自提点地址
    private String phoneNo;//联系电话
    private String ytoCode;  //圆通编码





    public VDeliveryPoint() {
    }

    public VDeliveryPoint(String branchCode, String cityCode, String pointCode, String pointName, String provinceCode) {
        this.branchCode = branchCode;
        this.cityCode = cityCode;
        this.pointCode = pointCode;
        this.pointName = pointName;
        this.provinceCode = provinceCode;
    }


    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getPointCode() {
        return pointCode;
    }

    public void setPointCode(String pointCode) {
        this.pointCode = pointCode;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getYtoCode() {
        return ytoCode;
    }

    public void setYtoCode(String ytoCode) {
        this.ytoCode = ytoCode;
    }
}
