package com.eiletxie.activemq.async_send;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * @Auther: CXIE
 * @Date: 2019/8/18 22:12
 * @Description:
 */
public class JmsProduce_AsyncSend {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String QUEUE_NAME = "Async-queue";


    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 设置为异步发送
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);

        TextMessage message = null;
        for (int i = 1; i <= 3; i++) {
            message = session.createTextMessage("async msg---" + i);
            message.setJMSMessageID(UUID.randomUUID().toString() + "--order ");
            String msgID = message.getJMSMessageID();
            activeMQMessageProducer.send(message, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(msgID + "has been send success!");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(msgID + "fail to end!");
                }
            });
        }

        activeMQMessageProducer.close();
        session.close();
        connection.close();

        System.out.println("---Queue消息发送到MQ 成功！---");
    }
}
