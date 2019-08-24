package com.eiletxie.boot_mq_produce.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * @Auther: CXIE
 * @Date: 2019/8/21 13:19
 * @Description:
 */
@Component
@EnableJms
public class ConfigBean {

    @Value("${myQueue}")
    private String myQueue;

    @Value("${myTopic}")
    private String myTopic;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(myQueue);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(myTopic);
    }
}
