package com.eiletxie.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.UUID;

/**
 * @Auther: CXIE
 * @Date: 2019/8/19 08:34
 * @Description:
 */
public class JmsConsumer {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String QUEUE_NAME = "jdbc01";

    public static void main(String[] args) throws Exception {

        System.out.println("2号消费者开始消费----" + UUID.randomUUID());
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);


        // 通过监听的方式来消费消息
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                //  instanceof 它的作用是判断其左边对象是否为其右边类的实例
                if (null != message && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("消费者接受到消息--- ：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
//                if(null != message && message instanceof  MapMessage){
//                    MapMessage mapMessage = (MapMessage)message;
//                    try {
//                        System.out.println("消费者接受到消息--- ：" + mapMessage.getString("testkey"));
//                    } catch (JMSException e){
//                        e.printStackTrace();
//                    }
//                }
            }
        });
        // 监听器写法，速度很快，要system.in.read()读写保证监听进程继续，不然直接就被关闭了
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

        System.out.println("消费MQ的消息成功！");

        /**
         * 1、 先生产消息，只启动1号消费者，问题：1号消费者还能消费吗？ Y
         * 2、 启动1号消费者，再启动2号消费者，问题：2号消费者还能消费吗？
         *  1号消费者消费全部信息，2号没有信息可消费了
         *
         *  3，先启动2个消费者，再生产6条消息，请问消费情况如何？
         *    一人一半 Y,有点负载均衡轮询模式的感觉
         */
    }
}
