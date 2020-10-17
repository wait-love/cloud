package start.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import start.entity.ApiServiceError;

/**
 * @Author: Jason
 * @Create: 2020/10/11  9:19
 * @Description 创建一个远程接口的调用
 */
@FeignClient(value = "eurekaclient", fallback = ApiServiceError.class)  // 其中value属性是指属性那个application.name 名称
public interface ApiService {

    @RequestMapping(value = "hello", method = RequestMethod.GET)    //这里是对应了具体的接口路径，需要到原模块中去寻找
    String test();
}
