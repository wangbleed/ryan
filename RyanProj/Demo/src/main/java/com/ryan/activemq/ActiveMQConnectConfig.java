package com.ryan.activemq;

import com.ryan.io.ConnectionConfig;
import org.apache.activemq.ActiveMQConnection;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */

public class ActiveMQConnectConfig implements ConnectionConfig{
    final int DEFAULT_BATCH_NUM = 1;
    String username;
    String password;
    String brokerUrl;
    String queueName;
    boolean bPersistent;
    int perBatchNum;

    public ActiveMQConnectConfig(String username, String password, String brokerUrl) {
        this.username = username;
        this.password = password;
        this.brokerUrl = brokerUrl;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
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
