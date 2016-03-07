package com.ryan.commons.mq.client.base;


import com.ryan.commons.mq.packet.MQPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ryan on 15/6/19.
 */
public abstract class MQReceiveClient extends BaseMQClient implements Runnable{

    private Timer timer;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public boolean connect() {
        boolean bFlag = super.connect();
        if(bFlag){
            try {
                if(bTopic) {
                    destination = session.createTopic(topicOrQueueName);
                    logger.info("start topic model...");
                }else {
                    destination = session.createQueue(topicOrQueueName);
                    logger.info("start queue model...");
                }
                consumer = session.createConsumer(destination);
            } catch (JMSException e){
                logger.error("connect mq error, cause:{}", e.getMessage());
            }
        }
        return bFlag;
    }

    @Override
    public boolean disconnect() {
        boolean bFlag = super.disconnect();
        if(bFlag){
            try{
                if(consumer != null)
                    consumer.close();
            } catch (JMSException e){
                logger.error("disconnection error...{}", e.getMessage());
            }
        }
        return bFlag;
    }

    @Override
    public void run() {
        this.receive();
    }

    public abstract void receive();

    protected void receiveTopicOrQueue() {
        logger.info("start receiveTopicOrQueue...{}", this.getClass().getName());
        if(!bTopic){
            timer = new Timer(true);

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        ObjectMessage message = (ObjectMessage)consumer.receive();
                        if(message != null){
                            MQPacket packet = (MQPacket)message.getObject();

                            threadPoolTaskExecutor.execute(new Thread(){
                                @Override
                                public void run() {
                                    sendSync(packet);
                                }
                            });
                        }
                    } catch (JMSException e) {
                        logger.error("{}, sendSync error, cause:{}", this.getClass().getName(), e.getMessage());
                    }
                }
            }, 1000, 1);

        } else {
            try {
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        if (message != null) {
                            try {
                                ObjectMessage obj = (ObjectMessage)message;
                                MQPacket packet = (MQPacket) obj.getObject();
                                sendSync(packet);
                            } catch (JMSException e){
                                logger.error("{}, sendSync error, cause:{}", this.getClass().getName(), e.getMessage());
                            }
                        }
                    }
                });
            } catch (JMSException e){
                logger.error("{}, sendSync error, cause:{}", this.getClass().getName(), e.getMessage());
            }
        }
    }

    public abstract void sendSync(MQPacket packet);
}
