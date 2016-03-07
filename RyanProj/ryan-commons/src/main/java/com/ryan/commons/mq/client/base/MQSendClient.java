package com.ryan.commons.mq.client.base;

import com.ryan.commons.mq.packet.MQPacket;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 * Created by ryan on 15/6/19.
 */
public abstract class MQSendClient extends BaseMQClient{

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
                producer = session.createProducer(destination);
                producer.setDeliveryMode(getActiveMQConnectFactory().getActiveMQConnectConfig().isbPersistent() ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
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
                if(producer != null)
                    producer.close();
            } catch (JMSException e){
                logger.error("disconnection error...{}", e.getMessage());
            }
        }
        return bFlag;
    }

    public void send(final MQPacket packet){
        try {
            ObjectMessage message = session.createObjectMessage();
            message.setObject(packet);
            producer.send(message);
        } catch (JMSException e) {
            logger.error("send queue msg error...{}", e.getMessage());
        }
    }
}
