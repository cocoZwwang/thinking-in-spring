package thinkinginspring.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Bean 实例化的示例
 */
public class BeanInstanceDemo
{
    public static void main(String[] args)
    {
        String location = "classpath:META-INF/bean-creation-context.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);

        User userByStaticMethod = (User) beanFactory.getBean("user-by-static-method");
        User userByInstanceMethod = (User) beanFactory.getBean("user-by-instance-method");
        User userByFactoryBean = (User) beanFactory.getBean("user-by-factory-bean");

        System.out.println("userByStaticMethod: " + userByStaticMethod);
        System.out.println("userByInstanceMethod: " + userByInstanceMethod);
        System.out.println("userByFactoryBean: " + userByFactoryBean);
        System.out.println("userByStaticMethod == userByInstanceMethod :" + (userByStaticMethod == userByInstanceMethod));
        System.out.println("userByStaticMethod == userByFactoryBean: " + (userByStaticMethod == userByFactoryBean));
        System.out.println("userByInstanceMethod == userByFactoryBean: " + (userByInstanceMethod == userByFactoryBean));

        Set set = new HashSet();

    }
}
