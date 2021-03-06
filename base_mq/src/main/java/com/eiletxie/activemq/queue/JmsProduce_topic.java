package com.eiletxie.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: CXIE
 * @Date: 2019/8/18 22:12
 * @Description:
 */
public class JmsProduce_topic {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String TOPIC_NAME = "jdbc_topic";

    public static void main(String[] args) throws JMSException {
        // 1、 创建连接工厂,按照给定的URL地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2、 通过连接工厂，获取连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 3、 创建会话session
        // 两个参数： 第一个叫事务/第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、 创建目的地（具体是队列还是主题）
        // 实现了 Inteface Destination
        Topic topic = session.createTopic(TOPIC_NAME);

        // 5、 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        // 6、 通过使用messageProducer 生产3条消息发送到MQ的队列里
        for (int i = 1; i <= 3; i++) {
            // 7、 创建消息,好比订单按照要求写的消息格式
            TextMessage textMessage = session.createTextMessage("topic persist msg---" + i);
            // 8、 通过messageProduce发送给mq
            messageProducer.send(textMessage);
        }

        // 9、 关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("---topic消息发送到MQ 成功！---");
    }
}
