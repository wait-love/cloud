package start.service;

/**
 * @Author: Jason
 * @Create: 2020/10/12  20:13
 * @Description 获取锁之后的操作
 */
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
