package com.eiletxie.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


/**
 * @Auther: CXIE
 * @Date: 2019/8/21 08:46
 * @Description:
 */
@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        // 获取应用上下文
        ApplicationContext ctx = new ClassPathXmlApplicationContext("src/main/resources/applicationContext.xml");
        // 获取自身bean，从而可以调用 jmsTemplate
        SpringMQ_Consumer consumer = (SpringMQ_Consumer) ctx.getBean("springMQ_Consumer");

        // lambuda写法
        String returnValue = (String) consumer.jmsTemplate.receiveAndConvert();

        System.out.println("--Spring 消费者收到的消息 : " + returnValue);


        System.out.println("--------send task over");

    }
}
