package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start.entity.DistributedLockHandler;
import start.entity.Lock;

/**
 * @Author: Jason
 * @Create: 2020/10/12  7:47
 * @Description 锁的控制类
 */
@RestController
public class LockController {

    @Autowired
    private DistributedLockHandler distributedLockHandler;

    @RequestMapping(value = "index2")
    public String index(){
        Lock lock = new Lock("jason", "distributrLock");
        if (distributedLockHandler.tryLock(lock)) {
            System.out.println("执行方法");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                distributedLockHandler.releaseLock(lock);
        }
        return "hello world";
    }
}
