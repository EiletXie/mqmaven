package com.eiletxie.boot_mq_produce;

import com.eiletxie.boot_mq_produce.produce.Queue_Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BootMqProduceApplicationTests {

    // java注解 Resoure
    @Resource
    private Queue_Produce queue_produce;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSend() throws Exception {
        queue_produce.produceMsg();
    }

}
