package text;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import save.config.RabbitMQConfig;
import save.entity.MyFile;
import save.util.FastDFSClient;

import java.io.IOException;
@SpringBootTest
@RunWith(SpringRunner.class)
public class eee {
    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void testRedis(){
        MyFile myFile = new MyFile(1,1,"qq","as","ss","sdd","asds");
        String s = JSON.toJSONString(myFile).replace("\"","");
        System.out.println(s);
    }
    @Autowired
    public RabbitTemplate rabbitTemplate;
    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"pawen.haha","pawen mq hello");
    }
}
