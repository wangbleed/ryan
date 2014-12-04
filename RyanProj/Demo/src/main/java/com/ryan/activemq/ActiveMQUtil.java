package com.ryan.activemq;

import com.ryan.io.ConnectionConfig;
import com.ryan.io.ConnectionIO;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-2
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 */
public class ActiveMQUtil implements ConnectionIO{

    private boolean bConnect = false;
    private ActiveMQConnectConfig activeMQConnectConfig;

    public ConnectionConfig getConfig() {
        return activeMQConnectConfig;
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

    public void send(com.ryan.io.packet.Message... messages){
        if(!bConnected() || messages == null || messages.length <= 0)
            return;
        try{
            ObjectMessage msg;
            int count = 0;
            for(com.ryan.io.packet.Message message : messages){
                msg = session.createObjectMessage();
                msg.setObject(message);
                producer.send(msg);
                producer.setTimeToLive(1000);
                ++ count;
                if(count >= activeMQConnectConfig.getPerBatchNum()){
                    session.commit();
                    count = 0;
                }
            }
            session.commit();
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean connect(ConnectionConfig config) {
        if(bConnect)
            disconnect();
        boolean bFlag = false;
        try{
            activeMQConnectConfig = (ActiveMQConnectConfig)config;
            initFactory();
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(activeMQConnectConfig.getQueueName());
            producer = session.createProducer(destination);
            producer.setDeliveryMode(activeMQConnectConfig.bPersistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
            bConnect = true;
        } catch (JMSException e){
            e.printStackTrace();
        }
        return bFlag;
    }

    @Override
    public boolean disconnect() {
        boolean bFlag = false;
        try{
            if(producer != null)
                producer.close();
            if(session != null)
                session.close();
            if(connection != null)
                connection.close();
            bConnect = false;
            bFlag = true;
        }catch (JMSException e){
            e.printStackTrace();
        }
        return bFlag;
    }
}
