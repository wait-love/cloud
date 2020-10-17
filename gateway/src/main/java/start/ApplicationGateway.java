package start;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author: Jason
 * @Create: 2020/10/10  20:25
 * @Description gateway启动类
 */
@SpringCloudApplication
public class ApplicationGateway {

    public static void main(String[] args){
        SpringApplication.run(ApplicationGateway.class, args);
    }
}
