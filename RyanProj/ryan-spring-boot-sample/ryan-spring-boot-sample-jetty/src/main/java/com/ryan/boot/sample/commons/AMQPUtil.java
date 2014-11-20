package com.ryan.boot.sample.commons;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 14-11-19
 * Time: 下午6:14
 * To change this template use File | Settings | File Templates.
 */
public class AMQPUtil {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public Sender getSender(){
        return new Sender();
    }

    @Bean
    public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }

//    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(this.connectionFactory);
        Object listener = new Object() {
            public void handleMessage(String msg) {
                System.out.println(msg);
            }
        };
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("foo");
        return container;
    }

}
