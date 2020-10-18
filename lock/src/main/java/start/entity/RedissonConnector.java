package start.entity;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: Jason
 * @Create: 2020/10/12  20:20
 * @Description 获取redissonConnector连接类
 */
@Component
public class RedissonConnector {
    RedissonClient redissonClient;

    @PostConstruct  //用于构造方法之后需要执行的方法，仅仅只是被服务器执行一次
    public void init(){
        redissonClient = Redisson.create();
    }

    public RedissonClient getRedissonClient(){
        return redissonClient;
    }
}
