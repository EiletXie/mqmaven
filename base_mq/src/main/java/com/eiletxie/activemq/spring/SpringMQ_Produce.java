package com.eiletxie.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @Auther: CXIE
 * @Date: 2019/8/21 08:47
 * @Description: Spring MQ生产者
 */
@Service
public class SpringMQ_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        // 获取应用上下文
        ApplicationContext ctx = new ClassPathXmlApplicationContext("src/main/resources/applicationContext.xml");
        // 获取自身bean，从而可以调用 jmsTemplate
        SpringMQ_Produce produce = (SpringMQ_Produce) ctx.getBean("springMQ_Produce");

        // lambuda写法,如果报lambuda level错误，那应该是 pom未指定jdk版本，并且本项目也未指定jdk8以上
        produce.jmsTemplate.send((session) -> {
            TextMessage textMessage = session.createTextMessage("*******Spring和ActiveMQ的整合 topic case test3...");
            return textMessage;
        });

   /*     produce.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("*******Spring和ActiveMQ的整合case...");
                return textMessage;
            }
        });*/

        System.out.println("--------send task over");

    }
}
