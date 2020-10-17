package start.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Jason
 * @Create: 2020/10/10  20:17
 * @Description client端的controller
 */
@RestController
@RefreshScope
public class HelloClient {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "hello")
    public String test(){
        return "hello client" + port;
    }
}
