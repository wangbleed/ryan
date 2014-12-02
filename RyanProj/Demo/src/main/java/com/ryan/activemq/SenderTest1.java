package com.ryan.activemq;

import com.ryan.entity.MsgPacket;
import com.ryan.packet.Message;
import com.ryan.packet.MessageContent;
import com.ryan.packet.MessageHead;
import org.apache.activemq.ActiveMQConnection;

import java.util.UUID;

public class SenderTest1 {
    private static final int SEND_NUMBER = 5;

    public static void main(String[] args) {
        String brokenUrl = "tcp://192.168.138.128:61616";
        String queueName = "pushs";
        ActiveMQUtil activeMQUtil = new ActiveMQUtil();
        ActiveMQConnectConfig config = new ActiveMQConnectConfig(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, brokenUrl);
        config.setbPersistent(true);
        config.setQueueName(queueName);
        activeMQUtil.connect(config);

        activeMQUtil.send(createMQMsg("demo2", "This is demo2"));

        activeMQUtil.disconnect();
    }

    public static Message createMQMsg(String title, String content){
        Message message = new Message();
        message.setHead(new MessageHead().setVersion("1.0").setSeq(10).setCmdType((byte)1));
        MsgPacket packet = new MsgPacket();
        packet.setContent(content);
        packet.setTitle(title);
        packet.setUid(UUID.randomUUID().toString().replace("-",""));
        message.setContent(new MessageContent(packet).setCompress(false).setCrypter(false));
        return message;
    }
}