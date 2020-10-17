package start;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author: Jason
 * @Create: 2020/10/10  20:08
 * @Description client端的启动类
 */
@SpringCloudApplication  //该注解是默认包含EnableDiscoveryClient 所以是无需显示的声明
public class ApplicationClient {

    public static void  main(String[] args){
        SpringApplication.run(ApplicationClient.class, args);
    }
}
