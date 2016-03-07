package com.ryan.commons.model.jinGang;

import java.io.Serializable;

/**
 * Created by admin on 2015/10/22.
 */
public class JGDeliveryOrder implements Serializable {
    private String collectionPay;	                    //    	代收货款金额
    private String receiverApp;		                    //    	详细地址
    private String receiverCity;	                    //    	城市
    private String receiverMobile;	                    //    	电话
    private String receiverProv;	                    //    	收件人姓名
    private String receiverName;	                    //    	省份
    private String receiverTown;	                    //    	区
    private String toPayment;		                    //    	货到付款金额
    private String waybillNo;		                    //    	面单号
    private String incrementType;	                    //    	增值服务类型()
    private String createtime;		                    //
    private String oper_code;		                    //       快递员工号

    public String getCollectionPay() {
        return collectionPay;
    }

    public void setCollectionPay(String collectionPay) {
        this.collectionPay = collectionPay;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getIncrementType() {
        return incrementType;
    }

    public void setIncrementType(String incrementType) {
        this.incrementType = incrementType;
    }

    public String getOper_code() {
        return oper_code;
    }

    public void setOper_code(String oper_code) {
        this.oper_code = oper_code;
    }

    public String getReceiverApp() {
        return receiverApp;
    }

    public void setReceiverApp(String receiverApp) {
        this.receiverApp = receiverApp;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverProv() {
        return receiverProv;
    }

    public void setReceiverProv(String receiverProv) {
        this.receiverProv = receiverProv;
    }

    public String getReceiverTown() {
        return receiverTown;
    }

    public void setReceiverTown(String receiverTown) {
        this.receiverTown = receiverTown;
    }

    public String getToPayment() {
        return toPayment;
    }

    public void setToPayment(String toPayment) {
        this.toPayment = toPayment;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }
}
