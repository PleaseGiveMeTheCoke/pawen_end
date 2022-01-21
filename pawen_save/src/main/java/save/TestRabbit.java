package save;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import save.config.RabbitMQConfig;
@Component
public class TestRabbit {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void testSend(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"pawen.haha","pawen mq hello");
    }
}
