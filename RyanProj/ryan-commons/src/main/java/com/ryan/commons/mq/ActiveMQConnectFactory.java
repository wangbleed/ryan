package com.ryan.commons.mq;

import com.ryan.commons.io.ConnectionIO;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jms.*;

/**
 * Created by Ryan
 */
public class ActiveMQConnectFactory implements ConnectionIO{

    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean bConnect = false;
    private ActiveMQConnectConfig activeMQConnectConfig;

    public void setActiveMQConnectConfig(ActiveMQConnectConfig activeMQConnectConfig) {
        this.activeMQConnectConfig = activeMQConnectConfig;
    }

    // ConnectionFactory ：连接工厂，JMS 用它创建连接
    ConnectionFactory connectionFactory;
    // Connection ：JMS 客户端到JMS
    Connection connection = null;
    // Session： 一个发送或接收消息的线程
    Session session;
    // Destination ：消息的目的地;消息发送给谁.
    Destination destination;
    // MessageProducer：消息发送者
    MessageProducer producer;

    private void initFactory(){
        connectionFactory = new ActiveMQConnectionFactory(
                activeMQConnectConfig.getUsername(),
                activeMQConnectConfig.getPassword(),
                activeMQConnectConfig.getBrokerUrl()
        );
    }

    public boolean bConnected() {
        return bConnect;
    }

    @Override
    public boolean connect() {
        logger.info("connect activemq...brokenUrl:{}", activeMQConnectConfig.getBrokerUrl());
        if(bConnect)
            disconnect();
        boolean bFlag = false;
        try{
            initFactory();
            connection = connectionFactory.createConnection();
            connection.start();
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            destination = session.createQueue(activeMQConnectConfig.getQueueName());
//            producer = session.createProducer(destination);
//            producer.setDeliveryMode(activeMQConnectConfig.bPersistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
            bConnect = true;
        } catch (JMSException e){
            logger.error("connect activemq error,brokenUrl:{}", activeMQConnectConfig.getBrokerUrl());
            e.printStackTrace();
        }
        return bFlag;
    }

    @Override
    public boolean disconnect() {
        logger.info("disconnect activemq...brokenUrl:{}", activeMQConnectConfig.getBrokerUrl());
        boolean bFlag = false;
        try{
            if(connection != null)
                connection.close();
            bConnect = false;
            bFlag = true;
        }catch (JMSException e){
            e.printStackTrace();
        }
        return bFlag;
    }

    public Connection getConnection() {
        return connection;
    }

    public ActiveMQConnectConfig getActiveMQConnectConfig() {
        return activeMQConnectConfig;
    }
}
