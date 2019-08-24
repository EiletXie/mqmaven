package com.eiletxie.activemq.delay_send;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.util.UUID;

/**
 * @Auther: CXIE
 * @Date: 2019/8/18 22:12
 * @Description:
 */
public class JmsProduce_DelaySend {

    public static final String ACTIVEMQ_URL = "tcp://10.1.100.34:61616";
    public static final String QUEUE_NAME = "Delay-queue";


    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);

        MessageProducer messageProducer = session.createProducer(queue);
        long delay = 3 * 1000;
        long period = 4 * 1000;
        int repeat = 5;

        for (int i = 1; i <= 3; i++) {
            TextMessage message = session.createTextMessage("delay msg---" + i);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
            messageProducer.send(message);
        }

        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("---Delay Queue消息发送到MQ 成功！---");
    }
}
