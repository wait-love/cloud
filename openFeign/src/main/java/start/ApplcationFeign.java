package start;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Jason
 * @Create: 2020/10/11  9:17
 * @Description openFeign启动类
 */
@SpringCloudApplication
@EnableFeignClients //标明这个注解，是表示这个模块是需要调用其他模块的接口的
@EnableHystrixDashboard //代表开启了dashboard界面查看熔断
public class ApplcationFeign {

    public static void main(String[] args){
        SpringApplication.run(ApplcationFeign.class, args);
    }

    @Bean
    public ServletRegistrationBean getServlcet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet );
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
