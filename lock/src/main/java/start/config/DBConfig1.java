package start.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Jason
 * @Create: 2020/10/12  21:39
 * @Description 数据库中配置类1
 */
//就是更为安全的从配置文件中获取到属性值
@ConfigurationProperties(prefix = "mysql.datasource.test1")
@SpringBootConfiguration
@Data
public class DBConfig1 {
    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
}
