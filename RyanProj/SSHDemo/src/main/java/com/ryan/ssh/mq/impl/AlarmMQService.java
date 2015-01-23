package com.ryan.ssh.mq.impl;

import com.ryan.ssh.cache.CacheQueueFIFO;
import com.ryan.ssh.mq.MQService;
import com.ryan.commons.packet.MessagePacketProtocalConstants;
import com.ryan.commons.packet.Packet;
import com.ryan.commons.packet.PacketHead;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.ByteArrayInputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-12-10
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 */
public class AlarmMQService extends MQService {

    public static final Logger mqLoger = LoggerFactory.getLogger("ALARMCENTER");

    private CacheQueueFIFO cacheQueueFIFO;

    public void setCacheQueueFIFO(CacheQueueFIFO cacheQueueFIFO) {
        this.cacheQueueFIFO = cacheQueueFIFO;
    }

    @Override
    public void init() {
        super.init();
        cacheQueueFIFO = CacheQueueFIFO.getInstance();
    }

    @Override
    public void run() {
        receive();
    }

    @Override
    protected void send(Packet packet) {
        super.send(packet);
    }

    @Override
    protected void receive() {
        mqLoger.info("start receive msg...");
        executorReceive.execute(
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            consumer.setMessageListener(new MessageListener() {
                                @Override
                                public void onMessage(Message message) {
                                    if (message != null) {
                                        try {
                                            BytesMessage msg = (BytesMessage)message;
                                            byte[] buff = new byte[(int) msg.getBodyLength()];
                                            msg.readBytes(buff);
                                            ByteArrayInputStream bais = new ByteArrayInputStream(buff, 0, buff.length);
                                            TTransport transport = new TIOStreamTransport(bais);
                                            TProtocol protocol = new TBinaryProtocol(transport);
                                            Packet packet = new Packet();
                                            packet.read(protocol);
                                            PacketHead head = packet.packetHead;
                                            TBase base = null;
                                            if(MessagePacketProtocalConstants.ALARM_COMMAND == head.cmd){
                                                base = packet.getPacketBody().getAPacket();
                                            } else if(MessagePacketProtocalConstants.ALARM_LIST_COMMAND == head.cmd){
                                                base = packet.getPacketBody().getAlArrPacket();
                                            } else if(MessagePacketProtocalConstants.ALARM_SOURCE_COMMAND == head.cmd){
                                                base = packet.getPacketBody().getAsPacket();
                                            } else if(MessagePacketProtocalConstants.NODE_COMMAND == head.cmd){
                                                base = packet.getPacketBody().getNodePacket();
                                            } else if(MessagePacketProtocalConstants.NODE_LIST_COMMAND == head.cmd){
                                                base = packet.getPacketBody().getNodeArrPacket();
                                            } else if(MessagePacketProtocalConstants.MAINTAIN_COMMAND == head.cmd){
                                                base = packet.getPacketBody().getMtPacket();
                                            }

                                            cacheQueueFIFO.addData2Cache(base);

                                        } catch (JMSException | TException e) {
                                            mqLoger.error("resolve message error, cause:{}", e.getMessage());
                                            logger.error("resolve message error, cause:{}", e.getMessage());
                                        }
                                    }
                                }
                            });
                        } catch (JMSException e) {
                            mqLoger.error("resolve message error, cause:{}", e.getMessage());
                            logger.error("consumer receive listener error, cause:{}", e.getMessage());
                        }
                    }
                }
        );
    }

}

