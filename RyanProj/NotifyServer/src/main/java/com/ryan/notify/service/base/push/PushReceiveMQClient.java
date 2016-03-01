package com.ryan.notify.service.base.push;

import com.ryan.commons.mq.client.base.MQReceiveClient;
import com.ryan.commons.mq.packet.MQPacket;
import com.ryan.commons.push.JPush;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by ryan on 15/10/15.
 */
public class PushReceiveMQClient extends MQReceiveClient {
    private boolean bConnect = false;

    @Autowired
    private PushSendMQClient pushSendMQClient;

    @Autowired
    private JPushServer jPushServer;


    @Override
    public boolean connect() {
        boolean f = super.connect();
        jPushServer.init();
        jPushServer.start();

        bConnect = true & f;
        return bConnect;
    }

    @Override
    public boolean disconnect() {
        boolean f = super.disconnect();
        jPushServer.stop();

        bConnect = true & f;
        return bConnect;
    }

    @Override
    public void receive() {
        if(!bConnect)
            return;

        this.receiveTopicOrQueue();

    }

    @Override
    public void sendSync(MQPacket packet){
        if(packet != null){
            if(packet.getHeader().getRetryTime() >= 3)
                return;

            boolean bFlag = false;
            try{
                jPushServer.sendJPush((JPush)packet.getBody().getContent());
                //发送给第三方Push组件
            } catch (Exception e){
                logger.error("Push send error, cause:{}", e.getMessage());
            }
            if(!bFlag){
                packet.getHeader().addRetryTime();
                packet.getHeader().setLastModifiedTime(new Date().getTime());
                pushSendMQClient.send(packet);
            }
        }
    }


}
