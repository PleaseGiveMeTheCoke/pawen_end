package save;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"save.dao"})
public class SaveApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SaveApplication.class, args);
        TestRedis bean = run.getBean(TestRedis.class);
        bean.testRedis();
        TestRabbit mq = run.getBean(TestRabbit.class);
        mq.testSend();
    }
}
