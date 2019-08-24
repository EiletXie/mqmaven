package com.eiletxie.activemq.queue;

import org.apache.activemq.broker.BrokerService;

/**
 * @Auther: CXIE
 * @Date: 2019/8/20 23:30
 * @Description:
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        // ActiveMQ也支持在VM中通信基于嵌入式的broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
