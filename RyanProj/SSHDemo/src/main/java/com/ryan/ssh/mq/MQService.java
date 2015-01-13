package com.hyron.alarmcenter.mq;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.hyron.commons.mq.ActiveMQConnectFactory;
import com.hyron.commons.packet.MQPacket;
import com.hyron.commons.service.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.*;
import java.io.Serializable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-10
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
public abstract class MQService extends Service{

    @Autowired
    private ActiveMQConnectFactory activeMQConnectFactory;
    private Integer sendThreadNum = 1;
    private Integer receiveThreadNum = 1;

    public void setSendThreadNum(Integer sendThreadNum) {
        this.sendThreadNum = sendThreadNum;
    }

    public void setReceiveThreadNum(Integer receiveThreadNum) {
        this.receiveThreadNum = receiveThreadNum;
    }

    private String queueNameBySend;
    private String queueNameByReceive;

    public void setQueueNameBySend(String queueNameBySend) {
        this.queueNameBySend = queueNameBySend;
    }

    public void setQueueNameByReceive(String queueNameByReceive) {
        this.queueNameByReceive = queueNameByReceive;
    }

    protected Executor executorReceive;
    protected Executor executorSend;
    private EventBus eventBus;

    protected boolean bConnected = false;

    // Connection ：JMS 客户端到JMS
    protected Connection connection = null;
    // Session： 一个发送或接收消息的线程
    protected Session sessionBySend;
    protected Session sessionByReceive;
    // Destination ：消息的目的地;消息发送给谁.
    protected Destination destinationBySend = null;
    protected Destination destinationByReceive = null;
    // MessageProducer：消息发送者
    protected MessageProducer producer = null;
    protected MessageConsumer consumer = null;

    @Override
    public void init() {
        executorSend = Executors.newFixedThreadPool(sendThreadNum, new EventThreadFactory());
        executorReceive = Executors.newFixedThreadPool(receiveThreadNum, new EventThreadFactory());
        eventBus = new AsyncEventBus("eventBus" , executorSend);
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
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            t.setDaemon(true);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    protected void connect(){
        try{
            if(StringUtils.isNotEmpty(queueNameByReceive) || StringUtils.isNotEmpty(queueNameBySend)){
                connection = activeMQConnectFactory.getConnection();
                bConnected = true;
                if(StringUtils.isNotEmpty(queueNameBySend)){
                    sessionBySend = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    destinationBySend = sessionBySend.createQueue(queueNameBySend);
                    producer = sessionBySend.createProducer(destinationBySend);
                    producer.setDeliveryMode(activeMQConnectFactory.getActiveMQConnectConfig().isbPersistent() ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
                }
                if(StringUtils.isNotEmpty(queueNameByReceive)){
                    sessionByReceive = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                    destinationByReceive = sessionByReceive.createQueue(queueNameByReceive);
                    consumer = sessionByReceive.createConsumer(destinationByReceive);
                }
            }
        }catch (JMSException e){
            logger.error("connect mq error, cause:{}", e.getMessage());
            e.printStackTrace();
        }
    }

    protected void disconnect(){
        try{
            if(consumer != null)
                consumer.close();
            if(sessionByReceive != null)
                sessionByReceive.close();
            if(sessionBySend != null)
                sessionBySend.close();
        }catch (JMSException e){

        }
    }

    protected void send(final MQPacket packet){
        executorSend.execute(new Thread(){
            @Override
            public void run() {
                try{
                    ObjectMessage message = sessionBySend.createObjectMessage();
                    message.setObject(packet);
                    producer.send(message);
                    sessionBySend.commit();
                }catch (JMSException e){
                    logger.error("send queue msg error...");
                }
            }
        });
    }

    protected abstract void receive();

    public MessageProducer getProducer() {
        return producer;
    }

    public MessageConsumer getConsumer() {
        return consumer;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
