package com.eiletxie.boot_mq_consumer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Auther: CXIE
 * @Date: 2019/8/21 14:36
 * @Description:
 */
@Component
public class Topic_Consumer {

    @JmsListener(destination = "${myTopic}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("**topic boot 消费者收到消息 ：" + textMessage.getText());
    }
}
