package thinking.in.spring.ioc.container.overview.dependency.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.Map;

/**
 * {@link Configuration} 在这里并不是必须的，不过标注它之后Bean的类被 CGLib 提升
 */
@Configuration
public class AnnotationApplicationAsIocContainerDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationAsIocContainerDemo.class);
        applicationContext.refresh();
        lookupInCollection(applicationContext);
        applicationContext.close();
    }

    @Bean
    public User user()
    {
        User user = new User();
        user.setId((long) 1);
        user.setName("weiss");
        return user;
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
}
