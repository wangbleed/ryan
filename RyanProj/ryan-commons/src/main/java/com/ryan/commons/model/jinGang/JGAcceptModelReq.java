package com.ryan.commons.model.jinGang;

import com.ryan.commons.entity.BaseEntity;

/**
 * 接单接口请求参数( 快递员接单了)
 * Created by bin on 2015/11/7.
 */
public class JGAcceptModelReq extends BaseEntity {
    private String clientID;                        //vip 客户标示 渠道字段
    private String logisticProviderID;              //物流公司标识
    private String txLogisticID;                    //物流平台物流号(订单号)
    private String tradeNo;                         //业务交易号
    private String mailNo;                          //面单号
    private String flag;                            //订单的flag标识，便于以后分拣和标识
    private String name;                            //用户姓名
    private String address;                         //用户地址
    private String poctCode;                        //用户邮编
    private String phone;                           //用户电话
    private String mobile;                          //用户移动电话
    private String prov;                            //用户所在省
    private String city;                            //用户所在市
    private String sendStartTIme;                   //物流上门取货时间段
    private String sendEndTime;                     //物流上门取货时间段
    private String itemName;                        //商品名称
    private String number;                          //数量
    private String special;                         //特殊商品性质，枚举值 （参考文档）
    private String remark;                          //商品描述
    private String insuranceValue;                  //保值金额（目前为成交额）
    private String packageOrNot;                    //是否打包（暂时没有使用，默认为false）

    private String createOrgCode;                   //网点代码
    private String createOrgName;                   //网点名称
    private String createUserCode;                  //员工编号
    private String createUserName;                  //员工姓名

    private String acceptOrgCode;                   //接单网点编号
    private String acceptOrgName;                   //接单网点名称
    private String acceptUserCode;                  //接单员工编号
    private String acceptUserName;                  //接单员工姓名

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getLogisticProviderID() {
        return logisticProviderID;
    }

    public void setLogisticProviderID(String logisticProviderID) {
        this.logisticProviderID = logisticProviderID;
    }

    public String getTxLogisticID() {
        return txLogisticID;
    }

    public void setTxLogisticID(String txLogisticID) {
        this.txLogisticID = txLogisticID;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoctCode() {
        return poctCode;
    }

    public void setPoctCode(String poctCode) {
        this.poctCode = poctCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSendStartTIme() {
        return sendStartTIme;
    }

    public void setSendStartTIme(String sendStartTIme) {
        this.sendStartTIme = sendStartTIme;
    }

    public String getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(String sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(String insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public String getPackageOrNot() {
        return packageOrNot;
    }

    public void setPackageOrNot(String packageOrNot) {
        this.packageOrNot = packageOrNot;
    }

    public String getCreateOrgCode() {
        return createOrgCode;
    }

    public void setCreateOrgCode(String createOrgCode) {
        this.createOrgCode = createOrgCode;
    }

    public String getCreateOrgName() {
        return createOrgName;
    }

    public void setCreateOrgName(String createOrgName) {
        this.createOrgName = createOrgName;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getAcceptOrgCode() {
        return acceptOrgCode;
    }

    public void setAcceptOrgCode(String acceptOrgCode) {
        this.acceptOrgCode = acceptOrgCode;
    }

    public String getAcceptOrgName() {
        return acceptOrgName;
    }

    public void setAcceptOrgName(String acceptOrgName) {
        this.acceptOrgName = acceptOrgName;
    }

    public String getAcceptUserCode() {
        return acceptUserCode;
    }

    public void setAcceptUserCode(String acceptUserCode) {
        this.acceptUserCode = acceptUserCode;
    }

    public String getAcceptUserName() {
        return acceptUserName;
    }

    public void setAcceptUserName(String acceptUserName) {
        this.acceptUserName = acceptUserName;
    }
}
