package start.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: Jason
 * @Create: 2020/10/12  21:47
 * @Description mybaties配置类1
 */
@SpringBootConfiguration
@MapperScan(basePackages = "start.mapper2", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig1 {

    /**
     *  配置数据源
     */
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource(DBConfig1 config) throws SQLException{
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(config.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(config.getPassword());
        mysqlXADataSource.setUser(config.getUsername());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        //设置Atomikos配置
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("dataSource");

        xaDataSource.setMinPoolSize(config.getMinPoolSize());
        xaDataSource.setMaxPoolSize(config.getMaxPoolSize());
        xaDataSource.setMaxLifetime(config.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(config.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(config.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(config.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(config.getMaxIdleTime());
        xaDataSource.setTestQuery(config.getTestQuery());
        return xaDataSource;
    }

    @Primary        //当一个接口被多个类实现时，添加此注解说明是会优先是实现此类
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
