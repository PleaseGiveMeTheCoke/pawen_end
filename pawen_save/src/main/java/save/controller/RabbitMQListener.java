package save.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {
    @RabbitListener(queues = "pawen_queue")
    public void ListenerQueue(Message message){
        String s = message.toString();
        System.out.println(s);
    }
}
