package com.eiletxie.boot_mq_produce.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @Auther: CXIE
 * @Date: 2019/8/21 13:23
 * @Description:
 */
@Component
public class Queue_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "*** boot msg:" + UUID.randomUUID().toString().substring(0, 6));
    }

//    // 间隔时间3S定时投递
//    @Scheduled(fixedDelay = 3000)
//    public void produceMsgScheduled() {
//        jmsMessagingTemplate.convertAndSend(queue,"*** boot msg:" + UUID.randomUUID().toString().substring(0,6));
//        System.out.println("--produceMsgScheduled--");
//    }
}
