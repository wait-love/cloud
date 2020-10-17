package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start.service.ApiService;

/**
 * @Author: Jason
 * @Create: 2020/10/11  9:35
 * @Description 测试api的控制器
 */

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("index")
    public String index(){
        return apiService.test();
    }
}
