package thinking.in.spring.dependency.lookup;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thinking.in.spring.ioc.container.overview.domain.User;
import java.util.Map;

public class TypeSafetyDependencyLookupDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(TypeSafetyDependencyLookupDemo.class);
        beanFactory.refresh();
        printBeanLookup("beanFactoryGetBean", () -> beanFactoryGetBean(beanFactory));
        printBeanLookup("objectFactoryGetBean", () -> objectFactoryGetBean(beanFactory));
        printBeanLookup("objectProviderGetBeanAvailable", () -> objectProviderGetBeanAvailable(beanFactory));
        printBeanLookup("listableBeanFactory", () -> listableBeanFactory(beanFactory));
        printBeanLookup("objectProviderStream", () -> objectProviderStream(beanFactory));
        beanFactory.close();
    }

    private static void beanFactoryGetBean(BeanFactory beanFactory)
    {
        beanFactory.getBean(User.class);
    }

    private static void objectFactoryGetBean(BeanFactory beanFactory)
    {
        ObjectFactory<User> userObjectFactory = beanFactory.getBeanProvider(User.class);
        userObjectFactory.getObject();
    }

    private static void objectProviderGetBeanAvailable(BeanFactory beanFactory)
    {
        ObjectProvider<User> userObjectProvider = beanFactory.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable();
        System.out.println("userObjectProvider.getIfAvailable : " + user);
        user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("userObjectProvider.getIfAvailable(suppler) : " + user);
    }


    private static void listableBeanFactory(ListableBeanFactory beanFactory)
    {
        Map<String, User> users = beanFactory.getBeansOfType(User.class);
    }

    private static void objectProviderStream(BeanFactory beanFactory)
    {
        ObjectProvider<User> provider = beanFactory.getBeanProvider(User.class);
        provider.stream().forEach(System.out::println);
    }

    private static void printBeanLookup(String message, Runnable runnable)
    {
        System.out.println("依赖查找--" + message);
        try
        {
            runnable.run();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("============================================");
    }
}
