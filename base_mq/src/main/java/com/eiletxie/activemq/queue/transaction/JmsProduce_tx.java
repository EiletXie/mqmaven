package com.eiletxie.activemq.queue.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: CXIE
 * @Date: 2019/8/18 22:12
 * @Description:
 */
public class JmsProduce_tx {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String QUEUE_NAME = "tx_queue01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 开启事务
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);

        MessageProducer messageProducer = session.createProducer(queue);
        for (int i = 1; i <= 3; i++) {
            TextMessage textMessage = session.createTextMessage("tx msg---" + i);
            messageProducer.send(textMessage);

        }

        messageProducer.close();
        // 事务session提交，不然会无反应
        session.commit();
        session.close();
        connection.close();

        System.out.println("---消息发送到MQ 成功！---");
    }
}
