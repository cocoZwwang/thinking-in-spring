package thinkinginspring.spring.bean.definition;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.container.overview.domain.User;
import thinking.in.spring.ioc.container.overview.factory.UserFactory;
import thinking.in.spring.ioc.container.overview.factory.impl.DefaultUserFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

import static java.util.ServiceLoader.load;

/**
 * Bean 实例化的特性方式
 * - 通过ServiceLoader实现
 * - 通过AutoWireCapableFactoryBean#creaBean(java.lang.String,int,boolean)
 */
public class SpecialBeanInstanceDemo {
    public static void main(String[] args) {
        String location = "classpath:META-INF/special-bean-creation-context.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        //直接通过Java api创建
        System.out.println("直接通过Java api创建==================");
        //直接通过java API来实例化serviceLoader
        demoServiceLoader();

        //通过注册ServiceFactoryBean
        //ServiceFactoryBean会直接返回需要被创建的实例，但是只会返回一个UserFactory对象，就算注册文件中注册了多个UserFactory对象
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        UserFactory userFactory = beanFactory.getBean(UserFactory.class);
        System.out.println("通过注册ServiceFactoryBean=================");
        System.out.println("UserFactory：" + userFactory);
        Map<String,UserFactory> map = listableBeanFactory.getBeansOfType(UserFactory.class);
        System.out.println("UserFactory array：" + map.values());

        //通过注册ServiceLoaderFactoryBean
        //ServiceFactoryBean会直接返回需要被创建的实例，不会直接返回被创建的对象
        ServiceLoader<UserFactory> loader =
                beanFactory.getBean("serviceLoaderUserFactoryBean", ServiceLoader.class);
        System.out.println("通过注册ServiceLoaderFactoryBean=================");
        disServiceLoader(loader);


        System.out.println("通过AutowireCapableBeanFactory 实例化UserFactory==================");
        //AutowireCapableBeanFactory 实例化UserFactory
//        UserFactory defaultUserFactory = beanFactory.createBean(DefaultUserFactory.class);
//        System.out.println("UserFactory: " + defaultUserFactory);

        System.out.println("通过AutowireCapableBeanFactory 实例化user==================");
        //AutowireCapableBeanFactory 实例化user
        //可以自动注入，但是返回的Bean不会被注册
        User user = beanFactory.createBean(User.class);
        //这里会抛异常：NoSuchBeanDefinitionException
        user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

    private static void demoServiceLoader() {
        ServiceLoader<UserFactory> loader = load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        disServiceLoader(loader);
    }

    private static void disServiceLoader(ServiceLoader<UserFactory> loader) {

        Iterator<UserFactory> iterator = loader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println("通过ServiceLoader获取UserFactory对象：" + userFactory);
        }
    }
}



