package start.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * @Author: Jason
 * @Create: 2020/10/11  20:57
 * @Description 分布式锁类
 */
@Component
public class DistributedLockHandler {
    private static final Logger logger = LoggerFactory.getLogger(DistributedLockHandler.class);
    private final static long LOCK_EXPIRE = 30 * 1000L; //单个业务持有锁的时间
    private final static long LOCK_TRY_INTERVAL = 30L; //默认30ms一次
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L; //默认尝试20s

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 尝试获取全局锁
     * @param lock 锁的名称
     * @return  true 获取成功，false获取失败
     */
    public boolean tryLock(Lock lock){
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     *尝试获取全局锁
     * @param lock      锁的名称
     * @param timeout   获取锁的超时时间，单位ms
     * @return      true 获取成功， false 获取失败
     */
    public boolean  tryLock(Lock lock, long timeout){
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     * @param lock     锁的名称
     * @param timeout  获取锁的超时时间
     * @param tryInterval 获取成功  true 代表成功  false 代表失败
     * @return
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval){
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     * @param lock           锁的名称
     * @param timeout        获取锁的超时时间
     * @param tryInterval    尝试间隔次数
     * @param lockExpireTime 锁的过期时间
     * @return
     */
    public boolean tryLock(Lock lock, long timeout, long tryInterval, long lockExpireTime){
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }
    /**
     * 操作redis获取全局锁
     * @param lock         锁的名称
     * @param timeout      获取的超时时间
     * @param tryInterval   多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return    true 获取成功 false 获取失败
     * @throws InterruptedException
     * 其原理是：在判断是否有key的基础上，进行循环,只有当不存在key才对其进行加锁操作，其他不对其进行任何操作
     */
    public boolean  getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        if(StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())){
            return false;
        }

        long startTime = System.currentTimeMillis();
        do{
            if (!stringRedisTemplate.hasKey(lock.getName())){
                ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
                ops.set(lock.getName(),lock.getValue(), lockExpireTime);
                return true;
            }else{//存在锁
                logger.debug("lock is exist");
            }
            //尝试超过设定值之后直接跳出循环
            if (System.currentTimeMillis() - startTime > timeout){
                return false;
            }
            try {
                Thread.sleep(tryInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (stringRedisTemplate.hasKey(lock.getName()));
        return false;
    }

    /**
     * 释放锁方法
     * @param lock
     */
    public void releaseLock(Lock lock){
        if (!StringUtils.isEmpty(lock.getName())){
            stringRedisTemplate.delete(lock.getName());

        }
    }
}
