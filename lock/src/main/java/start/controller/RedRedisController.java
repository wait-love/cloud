package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start.service.AquiredLockWorker;
import start.service.DistributedLocker;

/**
 * @Author: Jason
 * @Create: 2020/10/12  20:37
 * @Description 基于redLock实现
 */

@RestController
public class RedRedisController {

    @Autowired(required = true)
    private DistributedLocker distributedLocker;

    @RequestMapping("index")
    public String index() throws Exception{
        distributedLocker.lock("test",new AquiredLockWorker<Object>(){

            @Override
            public Object invokeAfterLockAquire() throws Exception {
                System.out.println("执行方法");
                Thread.sleep(5000);
                return null;
            }
        });
        return "hello world";
    }

}
