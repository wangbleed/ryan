package com.hyron.alarmcenter.mq.impl;

import com.hyron.alarmcenter.cache.CacheQueueFIFO;
import com.hyron.alarmcenter.mq.MQService;
import com.hyron.commons.packet.MQPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

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
    protected void send(MQPacket packet) {
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
                                            TextMessage objectMessage = (TextMessage) message;
                                            String jsonData = objectMessage.getText();
                                            //转化成对应的数据结构
                                            mqLoger.info("receive one msg...{}", jsonData);

                                            cacheQueueFIFO.addData2Cache(jsonData);

                                        } catch (JMSException e) {
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

