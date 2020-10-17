package start.entity;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.Resource;
import java.applet.AppletContext;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Jason
 * @Create: 2020/10/11  8:46
 * @Description 异常配置类
 */
@SpringBootConfiguration
/**
 * 使用ConfigurationProperties注解的类生效
 */
@EnableConfigurationProperties({ServerProperties.class, ResourceProperties.class})
public class ErrorHandlerConfiguration {

    private  final ServerProperties  serverProperties;

    private final ApplicationContext applicationContext;

    private final ResourceProperties resourceProperties;

    private final List<ViewResolver> viewResolvers;

    private final ServerCodecConfigurer serverCodecConfigurer;

    public ErrorHandlerConfiguration(ServerProperties serverProperties,
                                     ResourceProperties resourceProperties,
                                     ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                     ServerCodecConfigurer serverCodecConfigurer,
                                     ApplicationContext applicationContext){
        this.serverProperties = serverProperties;
        this.applicationContext = applicationContext;
        this.resourceProperties = resourceProperties;
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * gateway启动时执行此方法，将JsonExceptionHandler注入到spring容器中去
     * @param errorAttributes
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)  //主要是用来控制类的加载顺序，Highest_precedence代表最高级别
    public JsonExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes){
        JsonExceptionHandler jsonExceptionHandler = new JsonExceptionHandler(
                errorAttributes,
                this.resourceProperties,
                this.serverProperties.getError(),
                this.applicationContext
        );
        jsonExceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
        jsonExceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
        jsonExceptionHandler.setViewResolvers(this.viewResolvers);
        return jsonExceptionHandler;
    }

}
