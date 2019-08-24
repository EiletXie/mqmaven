package com.eiletxie.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: CXIE
 * @Date: 2019/8/19 08:34
 * @Description:
 */
public class JmsConsumer_topic_persist {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String TOPIC_NAME = "Topic-jdbc-Persist";

    public static void main(String[] args) throws Exception {

        System.out.println("** 科比开始消费----");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("科比");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "it's a remark");


        connection.start();

        Message message = topicSubscriber.receive();
        while (null != message) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("**收到的持久化的Topic " + textMessage.getText());
            message = topicSubscriber.receive(3000L);
        }


        session.close();
        connection.close();

        System.out.println("消费MQ的消息成功！");


    }
}

