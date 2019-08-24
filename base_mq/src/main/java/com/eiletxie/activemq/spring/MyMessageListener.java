package com.eiletxie.activemq.spring;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Auther: CXIE
 * @Date: 2019/8/21 10:26
 * @Description:
 */
@Component
public class MyMessageListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        if (null != message && message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}
