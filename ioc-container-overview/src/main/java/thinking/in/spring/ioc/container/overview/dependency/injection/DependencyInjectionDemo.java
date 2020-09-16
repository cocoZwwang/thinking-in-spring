package thinking.in.spring.ioc.container.overview.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import thinking.in.spring.ioc.container.overview.domain.User;
import thinking.in.spring.ioc.container.overview.repository.UserRepository;

import java.util.Collection;

/**
 * Spring IOC 依赖注入
 * 根据名称注入
 * 根据类型注入
 * - 单个Bean对象
 * - 集合Bean对象
 * 注入非容器内建Bean对象
 * 注入非Bean对象
 * 注入类型
 * - 实时注入
 * - 延时注入
 * Spring IOC 依赖来源
 * - 自定义Bean
 * - 容器内建Bean
 * - 容器内依赖（例如：beanFactory）
 */
public class DependencyInjectionDemo
{
    public static void main(String[] args)
    {
        String location = "classpath:META-INF/dependency-injection-context.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);

        //依赖来源一：自定义Bean
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);
        System.out.println(userRepository);
//        Collection<User> users = userRepository.getUsers();
//        System.out.println(users);

        //beanFactory和userRepository注入的beanFactory不是同一个对象
        //依赖来源二：内建依赖
//        System.out.println(userRepository.getBeanFactory() == beanFactory);

        //userRepository里面注入的applicationContext == beanFactory
        //依赖来源二：内建依赖
        /**
         * 1 ApplicationContext就是BeanFactory
         * 2 ApplicationContext是BeanFactory的子接口
         * 3 ApplicationContext的实现包含BeanFactory（组合模式），BeanFactory提供IOC容器的基本框架比如
         * 依赖查找和依赖注入，而ApplicationContext则是在BeanFactory的基础添加了一些企业级的java ee应用特性
         * 比如：aop、国际化支持、事件发布、配置元信息（注解元信息、外部化配置。。。）等等
         * 4 所以ApplicationContext和BeanFactory都是Spring IOC的容器，
         * 但是BeanFactory对象！= ApplicationContext对象，他们是具有包含的层次关系（组合设计模式）
         */
//        System.out.println(userRepository.getContextObjectFactory().getObject() == beanFactory);

        //依赖来源三：内建Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("根据类型 获取 内建Bean Environment：" + environment);

        //beanFactory无法直接查找BeanFactory,会抛出错误，说明依赖注入和依赖查找是不同的依赖来源？
        System.out.println(beanFactory.getBean(BeanFactory.class));
    }

}
