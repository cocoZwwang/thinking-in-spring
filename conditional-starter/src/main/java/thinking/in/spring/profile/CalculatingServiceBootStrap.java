package thinking.in.spring.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 使用@Profile实现Bean定义的条件装配
 */
@ComponentScan(basePackageClasses = CalculatingServiceBootStrap.class)
public class CalculatingServiceBootStrap {

    static {
        //通过Java系统属性设置Spring Profile
        // 以下语句等效于ConfigurableEnvironment.setActiveProfiles("Java8");
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,"Java8");
        // 以下语句等效于ConfigurableEnvironment.setDefaultProfiles("Java7");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,"Java7");
    }
    public static void main(String[] args) {
        //构建应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册驱动类
        context.register(CalculatingServiceBootStrap.class);
        //刷选上下文
        context.refresh();
        //获取CalculatingService Bean
        CalculatingService service = context.getBean(CalculatingService.class);
        //输出结果
        service.sum(1,2,3,4,5);
        //关闭上下文
        context.close();
    }
}
