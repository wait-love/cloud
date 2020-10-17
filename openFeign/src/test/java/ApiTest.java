import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import start.ApplcationFeign;
import start.service.ApiService;

/**
 * @Author: Jason
 * @Create: 2020/10/11  9:23
 * @Description api测试类
 */
@SpringBootTest(classes = ApplcationFeign.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ApiTest {

    @Autowired
    private ApiService apiService;

    @Test
    public void test(){
        System.out.println(apiService.test());
    }
}
