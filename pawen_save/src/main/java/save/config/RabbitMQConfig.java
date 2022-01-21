package save.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "pawen_topic_exchange";
    public static final String QUEUE_NAME = "pawen_queue";
    //交换机
    @Bean("pawenExchange")
    public Exchange pawenExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }
    //队列
    @Bean("pawenQueue")
    public Queue pawenQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }
    //绑定
    @Bean
    public Binding bindQueueExchange(@Qualifier("pawenQueue") Queue queue, @Qualifier("pawenExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("pawen.#").noargs();
    }

}
