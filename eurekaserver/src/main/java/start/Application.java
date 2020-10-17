package start;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: Jason
 * @Create: 2020/10/10  7:36
 * @Description 启动类
 */
@SpringCloudApplication
@EnableEurekaServer  //表示这个类就是一个eureka server中心
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
