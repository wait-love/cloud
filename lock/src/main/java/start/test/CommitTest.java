package start.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import start.ApplicationLock;
import start.entity.User;
import start.service.UserService;

/**
 * @Author: Jason
 * @Create: 2020/10/13  7:14
 * @Description 分布式事务的测试
 */
@SpringBootTest(classes = ApplicationLock.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CommitTest {

    @Autowired
    private UserService userService;

    @Test
    public void userTest(){
        User user = new  User("jason", 28);
        try {
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
