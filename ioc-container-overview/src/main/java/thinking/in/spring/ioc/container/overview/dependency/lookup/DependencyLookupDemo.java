package thinking.in.spring.ioc.container.overview.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.container.overview.annotation.Super;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.Map;

public class DependencyLookupDemo
{

    public static void main(String[] args)
    {
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);
        //实时查找
//        lookupInRealTime(beanFactory);

        //利用ObjectFactory接口实现延时查找
        lookupInLazy(beanFactory);

//        //利用类型查找ObjectFactory
//        lookupInType(beanFactory);
//
//        //利用类型集合查找
//        lookupInCollection(beanFactory);
//
//        //根据注解查找
//        lookupInAnnotation(beanFactory);
    }


    private static void lookupInRealTime(BeanFactory beanFactory)
    {
        User user = (User) beanFactory.getBean("user");
        System.out.println("根据名称实时查找：" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory)
    {
        ObjectFactory<User> userObjFactory = (ObjectFactory<User>) beanFactory.getBean("userObjFactory");
        System.out.println("获取ObjectFactory");
        User user = userObjFactory.getObject();
        System.out.println("根据名称延迟查找：" + user);
    }

    private static void lookupInType(BeanFactory beanFactory)
    {
        User user = beanFactory.getBean(User.class);
        System.out.println("根据类型查找：" + user);
    }

    private static void lookupInCollection(BeanFactory beanFactory)
    {
        if (beanFactory instanceof ListableBeanFactory)
        {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("根据类型集合查找：" + users);
        }
    }

    private static void lookupInAnnotation(BeanFactory beanFactory)
    {
        if (beanFactory instanceof ListableBeanFactory)
        {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("根据注解查找：" + users);
        }
    }
}
