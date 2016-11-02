package com.ryan.commons.service;

/**
 * Created by Ryan on 15/5/15.
 */
public class RegServiceVo {

    /**
     * 本地IP地址
     */
    protected String serviceIp;
    /**
     * 本地服务端口
     */
    protected int servicePort;
    /**
     * 本应用注册的扩展信息
     */
    protected String serverMessage;

    /**
     * 本地服务端口
     *
     * @return
     */
    public int getServicePort() {
        return servicePort;
    }

    /**
     * 本地服务端口
     *
     * @param servicePort
     */
    public void setServicePort(int servicePort) {
        this.servicePort = servicePort;
    }

    /**
     * 本应用注册的扩展信息
     *
     * @return
     */
    public String getServerMessage() {
        return serverMessage == null ? null : this.serverMessage.trim();
    }

    /**
     * 本应用注册的扩展信息
     *
     * @param serverMessage
     */
    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    /**
     * 本地IP地址
     */
    public String getServiceIp() {
        return this.serviceIp == null ? null : this.serviceIp.trim();
    }

    /**
     * 本地IP地址
     */
    public void setServiceIp(String serviceIp) {
        this.serviceIp = serviceIp;
    }

    /**
     * 本地IP地址，主要兼容老接口，请使用新的属性
     *
     * @deprecated
     */
    public void setSrerviceIp(String srerviceIp) {
        this.serviceIp = srerviceIp;
    }
}
