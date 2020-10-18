package start.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Jason
 * @Create: 2020/10/12  21:44
 * @Description 数据库配置类2
 */
@ConfigurationProperties(prefix = "mysql.datasource.test2")
@SpringBootConfiguration
@Data
public class DBConfig2 {
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
