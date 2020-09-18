package thinking.in.spring.autoconfigure;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * spring framework 需要调整到5.0.6.release版本
 */
@EnableAutoConfiguration(exclude = SpringApplicationAdminJmxAutoConfiguration.class)
public class EnableAutoConfigurationBootStrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EnableAutoConfigurationBootStrap.class)
                //非web应用
                .web(WebApplicationType.NONE)
                //运行
                .run(args)
                //关闭上下文
                .close();
    }
}
