package com.ryan.notify.service.base.sms;

import com.ryan.commons.mq.client.AbstractSMSReceiveClient;
import com.ryan.commons.mq.packet.MQPacket;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by ryan on 15/5/15.
 * 短信客户端
 */
public class SMSReceiveClient extends AbstractSMSReceiveClient {

    @Autowired
    private SmsMessageSender smsMessageSender;

    @Autowired
    private SMSMQClient smsmqClient;

    @Override
    public void receive() {
        this.receiveTopicOrQueue();
    }

    @Override
    public void sendSync(MQPacket packet){
        if(packet != null){
            if(packet.getHeader().getRetryTime() >= 3)
                return;

            boolean bFlag = false;
            try {
                bFlag = smsMessageSender.sendMessage((SmsMessage)packet.getBody().getContent());
            } catch (Exception e) {
                logger.error("SMS send error, cause:{}", e.getMessage());
            }
            //重新放入MQ
            if(!bFlag){
                packet.getHeader().addRetryTime();
                packet.getHeader().setLastModifiedTime(new Date().getTime());
                smsmqClient.send(packet);
            }
        }
    }
}
