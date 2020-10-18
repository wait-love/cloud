package start.entity;

import lombok.Data;

/**
 * @Author: Jason
 * @Create: 2020/10/11  16:37
 * @Description 全局锁类 主要有名称与value值
 */
@Data
public class Lock {

    private String name;
    private String value;

    public Lock(String name, String value){
        this.name = name;
        this.value = value;
    }
}
