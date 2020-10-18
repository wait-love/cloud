package start.entity;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import start.service.AquiredLockWorker;
import start.service.DistributedLocker;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jason
 * @Create: 2020/10/12  20:26
 * @Description redis锁
 */
@Component
public class RedisLock implements DistributedLocker {

    private final static String LOCKER_PREFIX = "lock";

    @Autowired
    RedissonConnector redisConnection;

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception {
        return lock(resourceName, worker, 100);
    }

    /**
     *感觉更像一种设计模式一样
     * @param resourceName
     * @param worker
     * @param lockTime
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime) throws Exception {
        RedissonClient  redisson = redisConnection.getRedissonClient();
        RLock lock = redisson.getLock(LOCKER_PREFIX + resourceName);
        //等待100秒之后，自动释放锁
        boolean success = lock.tryLock(100, lockTime, TimeUnit.SECONDS);
        if(success){
            try{
                return worker.invokeAfterLockAquire();
            }finally {
                lock.unlock();
            }
        }
            throw new UnableToAquireLockException();
    }
}
