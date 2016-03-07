package com.ryan.commons.mq;

import com.ryan.commons.util.io.ConnectionConfig;
import org.apache.activemq.ActiveMQConnection;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Ryan
 */
public class ActiveMQConnectConfig implements ConnectionConfig {
    final int DEFAULT_BATCH_NUM = 1;
    String username;
    String password;
    String brokerUrl;
    boolean bPersistent;
    int perBatchNum;

    public ActiveMQConnectConfig(){}

    public ActiveMQConnectConfig(String username, String password, String brokerUrl) {
        this.username = username;
        this.password = password;
        this.brokerUrl = brokerUrl;
    }

    @Override
    public void init() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getUsername() {
        if(StringUtils.isEmpty(username))
            username = ActiveMQConnection.DEFAULT_USER;
        return username;
    }

    public String getPassword() {
        if(StringUtils.isEmpty(password))
            password = ActiveMQConnection.DEFAULT_PASSWORD;
        return password;
    }

    public String getBrokerUrl() {
        if(StringUtils.isEmpty(brokerUrl))
            return ActiveMQConnection.DEFAULT_BROKER_URL;
        return brokerUrl;
    }

    public boolean isbPersistent() {
        return bPersistent;
    }

    public void setbPersistent(boolean bPersistent) {
        this.bPersistent = bPersistent;
    }

    public int getPerBatchNum() {
        if(perBatchNum <= 0)
            return DEFAULT_BATCH_NUM;
        return perBatchNum;
    }

    public void setPerBatchNum(int perBatchNum) {
        this.perBatchNum = perBatchNum;
    }
}
