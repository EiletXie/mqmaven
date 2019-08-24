package com.eiletxie.activemq.redelivery;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: CXIE
 * @Date: 2019/8/19 08:34
 * @Description:
 */
public class JmsConsumer_Redelivery {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String QUEUE_NAME = "Redelivery-queue";

    public static void main(String[] args) throws Exception {

        System.out.println("** Delay 消费者开始消费----");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        connection.start();


        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费者接受Redelivery消息--- ：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 监听器写法，速度很快，要system.in.read()读写保证监听进程继续，不然直接就被关闭了
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

        System.out.println("消费MQ的消息成功！");


    }
}

