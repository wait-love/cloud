package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Jason
 * @Create: 2020/10/11  16:30
 * @Description 分布式锁的启动类
 */

@SpringBootApplication
public class ApplicationLock {

    public static void main(String[] args){
        SpringApplication.run(ApplicationLock.class, args);
    }
}
