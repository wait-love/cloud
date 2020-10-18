package start.service;

import org.springframework.stereotype.Component;

/**
 * @Author: Jason
 * @Create: 2020/10/12  20:14
 * @Description 分布式锁的管理类
 */
public interface DistributedLocker {

    /**
     * 获取锁
     * @param resourceName   锁的名称
     * @param worker         获取锁后的处理类
     * @param <T>
     * @return    处理完具体的业务逻辑要返回的数据
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception;

    <T> T lock(String resourceName, AquiredLockWorker<T> worker , int lockTime) throws Exception;
}

