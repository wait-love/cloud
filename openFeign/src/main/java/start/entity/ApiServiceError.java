package start.entity;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import start.service.ApiService;

/**
 * @Author: Jason
 * @Create: 2020/10/11  9:33
 * @Description api出错的类编写
 */
@Component
public class ApiServiceError implements ApiService {

    @Override
    public String test() {
        return "服务器发生故障";
    }
}
