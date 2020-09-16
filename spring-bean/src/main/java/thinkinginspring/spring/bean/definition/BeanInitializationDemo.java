package thinkinginspring.spring.bean.definition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import thinking.in.spring.ioc.container.overview.domain.User;
import thinking.in.spring.ioc.container.overview.factory.UserFactory;
import thinking.in.spring.ioc.container.overview.factory.impl.DefaultUserFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Bean 初始化 实例
 */
@Configuration
public class BeanInitializationDemo
{
    //@Autowired会触发依赖查找，所以也会触发延迟初始化
    // 我觉得应该更适合叫做延迟实例化
    @Autowired UserFactory userFactory;

    public static void main(String[] args) throws InterruptedException
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(BeanInitializationDemo.class);
        beanFactory.refresh();
        System.out.println("依赖查找之前。。。");
        UserFactory userFactory = beanFactory.getBean(UserFactory.class);
        System.out.println("依赖查找之后。。。");
        beanFactory.close();
        TimeUnit.SECONDS.sleep(10);
        System.gc();
        TimeUnit.SECONDS.sleep(10);
        Map<String,String> map = new HashMap<>();
    }

    @Bean(initMethod = "initByAnnotation",destroyMethod = "destroyByAnnotation")
    @Lazy
    public UserFactory userFactory()
    {
        return new DefaultUserFactory();
    }
}
