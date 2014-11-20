package com.ryan.boot.sample.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public class Sender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    public void setUpQueue() {
        logger.info("add queue...");
        this.amqpAdmin.declareQueue(new Queue("foo"));
    }

    @Scheduled(fixedDelay = 1000L)
    public void send() {
        logger.info("send queue...");
        this.rabbitTemplate.convertAndSend("foo", "hello");
    }
}
