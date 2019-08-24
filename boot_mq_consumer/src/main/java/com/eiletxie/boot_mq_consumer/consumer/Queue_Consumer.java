package com.eiletxie.boot_mq_consumer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Auther: CXIE
 * @Date: 2019/8/21 14:15
 * @Description:
 */
@Component
public class Queue_Consumer {

//    // 目前测试topic，就关闭 queue的监听
//    @JmsListener(destination = "${myQueue}")
//    public void receive(TextMessage textMessage) throws JMSException {
//        System.out.println("** boot 消费者收到消息 ：" + textMessage.getText());
//    }
}
