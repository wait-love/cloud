package start;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: Jason
 * @Create: 2020/10/11  10:06
 * @Description config启动类
 */
@SpringCloudApplication
@EnableConfigServer
public class ApplicationConfig {

    public static void main(String[]  args){
        SpringApplication.run(ApplicationConfig.class, args);
    }
}

