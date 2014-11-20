package com.ryan.boot.sample.commons;

import com.ryan.boot.sample.Sender;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(
                this.connectionFactory);
        Object listener = new Object() {
            @SuppressWarnings("unused")
            public void handleMessage(String foo) {
                System.out.println(foo);
            }
        };
        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("foo");
        return container;
    }

    public static void main(String[] args){
        SpringApplication.run(AMQPUtil.class, args);
    }
}
