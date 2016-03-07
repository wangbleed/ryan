package com.ryan.commons.model.jinGang;

import com.ryan.commons.entity.BaseEntity;

import java.io.Serializable;

/**
 * 正常签收及异常签收接口请求参数
 * Created by admin on 2015/10/26.
 */
public class JGTransferSignModelReq  extends BaseEntity implements Serializable{
    private String waybillNO;                               //单号
    private String amountAgency;                            //代付金额
    private String auxOpCode;                               //辅助操作码
    private String createOrgCode;                           //派件网店代码
    private String createTime;                              //签到时间 (精确到时分秒)
    private String createUserCode;                          //派件员工代码
    private String createUserName;                          //派件员名称
    private String deviceType;                              //设备类型 Android  IOS
    private String opcode;                                  //操作码
    private String signoffTypeCode;                         //签收人类型
    private String receiverSignoff;                         //签收人姓名
    private String routeCode;                               //路由检查代码
    private String deliveryFailReason;                      //失败原因
    private String deliveryFailReasonCode;                  //失败code
    private String hasSignPic;                              //是否有签收图片(0为没有 1为有)
    private String modifytime;                              //修改时间(精确到时分秒)
    private String mac;                                     //mac 地址

    private String tokenID;

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public String getWaybillNO() {
        return waybillNO;
    }

    public void setWaybillNO(String waybillNO) {
        this.waybillNO = waybillNO;
    }

    public String getAmountAgency() {
        return amountAgency;
    }

    public void setAmountAgency(String amountAgency) {
        this.amountAgency = amountAgency;
    }

    public String getAuxOpCode() {
        return auxOpCode;
    }

    public void setAuxOpCode(String auxOpCode) {
        this.auxOpCode = auxOpCode;
    }

    public String getCreateOrgCode() {
        return createOrgCode;
    }

    public void setCreateOrgCode(String createOrgCode) {
        this.createOrgCode = createOrgCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getSignoffTypeCode() {
        return signoffTypeCode;
    }

    public void setSignoffTypeCode(String signoffTypeCode) {
        this.signoffTypeCode = signoffTypeCode;
    }

    public String getReceiverSignoff() {
        return receiverSignoff;
    }

    public void setReceiverSignoff(String receiverSignoff) {
        this.receiverSignoff = receiverSignoff;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getDeliveryFailReason() {
        return deliveryFailReason;
    }

    public void setDeliveryFailReason(String deliveryFailReason) {
        this.deliveryFailReason = deliveryFailReason;
    }

    public String getDeliveryFailReasonCode() {
        return deliveryFailReasonCode;
    }

    public void setDeliveryFailReasonCode(String deliveryFailReasonCode) {
        this.deliveryFailReasonCode = deliveryFailReasonCode;
    }

    public String getHasSignPic() {
        return hasSignPic;
    }

    public void setHasSignPic(String hasSignPic) {
        this.hasSignPic = hasSignPic;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
