package com.ryan.commons.mq.client.base;

import com.ryan.commons.mq.ActiveMQConnectFactory;
import com.ryan.commons.util.io.ConnectionIO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ryan
 */
public abstract class BaseMQClient implements ConnectionIO {

    public static final Logger logger = LoggerFactory.getLogger(BaseMQClient.class);

    private ActiveMQConnectFactory activeMQConnectFactory;

    public void setActiveMQConnectFactory(ActiveMQConnectFactory activeMQConnectFactory) {
        this.activeMQConnectFactory = activeMQConnectFactory;
    }

    public ActiveMQConnectFactory getActiveMQConnectFactory() {
        return activeMQConnectFactory;
    }

    protected String topicOrQueueName;
    protected Integer threadNum = 1;

    protected boolean bConnected = false;

    protected boolean bTopic = false;
    // Connection ：JMS 客户端到JMS
    protected Connection connection = null;
    // Session： 一个发送或接收消息的线程
    protected Session session;
    // Destination ：消息的目的地;消息发送给谁.
    protected Destination destination = null;
    // MessageProducer：消息发送者
    protected MessageProducer producer = null;
    protected MessageConsumer consumer = null;

    public void setQueueName(String topicOrQueueName) {
        this.topicOrQueueName = topicOrQueueName;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void init() {
        logger.info("start MQService...ServiceName:{}", this.getClass());
        connect();
    }

    /**
     * The default thread factory
     */
    static class EventThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final ThreadGroup group;

        EventThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "event-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            t.setDaemon(true);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    @Override
    public boolean connect(){
        logger.info("start connect MQName:{}", topicOrQueueName);
        try{
            if(StringUtils.isNotEmpty(topicOrQueueName)){
                connection = activeMQConnectFactory.getConnection();
                connection.start();
                bConnected = true;
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }
        }catch (JMSException e){
            logger.error("connect mq error, cause:{}", e.getMessage());
        }
        return bConnected;
    }

    @Override
    public boolean disconnect(){
        try{
            bConnected = false;
            if(consumer != null)
                consumer.close();
            if(session != null)
                session.close();
        }catch (JMSException e){
            logger.error("disconnection error...{}", e.getMessage());
        }
        return bConnected;
    }

    public MessageProducer getProducer() {
        return producer;
    }

    public MessageConsumer getConsumer() {
        return consumer;
    }

    public boolean isbTopic() {
        return bTopic;
    }

    public void setbTopic(boolean bTopic) {
        this.bTopic = bTopic;
    }
}
