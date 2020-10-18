package start.entity;

import lombok.Data;

/**
 * @Author: Jason
 * @Create: 2020/10/13  7:08
 * @Description 用户类
 */
@Data
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
