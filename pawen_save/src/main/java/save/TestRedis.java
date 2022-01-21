package save;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestRedis {
@Autowired
    RedisTemplate redisTemplate;
public void  testRedis(){
    redisTemplate.opsForValue().set("hello","redis");


}
}
