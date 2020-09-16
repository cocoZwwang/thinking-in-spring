package thinkinginspring.spring.bean.definition;

import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.ioc.container.overview.factory.UserFactory;
import thinking.in.spring.ioc.container.overview.factory.impl.DefaultUserFactory;

public class SingletonBeanRegistrationDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SingletonBeanRegistrationDemo.class);
        //创建一个外部单体的实例,外部单体对象不在Spring IOC 生命周期的管理范围内
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry beanFactory = applicationContext.getBeanFactory();
//        ConfigurableListableBeanFactory beanFactory =  applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        //启动上下文
        applicationContext.refresh();
        //依赖查找刚刚注入的外部依赖
        UserFactory factory = (UserFactory) applicationContext.getBean("userFactory");
        System.out.println("userFactory == factory: " + (userFactory == factory));

//        UserFactory factory1 = (UserFactory) applicationContext.getBean("userFactory1");
//        System.out.println(factory1);
        applicationContext.close();
    }

    @Bean
    public UserFactory userFactory1()
    {
        return new DefaultUserFactory();
    }
}
