package com.eiletxie.activemq.queue.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: CXIE
 * @Date: 2019/8/19 08:34
 * @Description:
 */
public class JmsConsumer_tx {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String QUEUE_NAME = "tx_queue01";

    public static void main(String[] args) throws Exception {

        System.out.println("2号消费者开始消费----");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);


        while (true) {
            TextMessage textMessage = (TextMessage) consumer.receive(4000L);
            if (null != textMessage) {
                System.out.println("消费者收到消息: " + textMessage.getText());

                //手动签收模式中 消费者验收消息，消息才算消费
                textMessage.acknowledge();
            } else {
                break;
            }
        }
        consumer.close();
        // 如果开启事务不commit，则消息将会被重复消费，不管有没有签收
        session.close();
        connection.close();

        System.out.println("消费MQ的消息成功！");


    }
}
