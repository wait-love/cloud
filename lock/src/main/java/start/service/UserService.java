package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entity.User;
import start.mapper1.UserMapper1;
import start.mapper2.UserMapper2;

/**
 * @Author: Jason
 * @Create: 2020/10/13  7:04
 * @Description 用户service
 */
@Service
public class UserService {

    @Autowired
    private UserMapper1 userMapper1;

    @Autowired
    private UserMapper2 userMapper2;

    public void addUser(User user) throws Exception{
        userMapper1.addUser(user.getName(), user.getAge());
        userMapper2.addUser(user.getName(), user.getAge());
    }

}
