package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.dependency.annotation.UserGroup;
import thinking.in.spring.ioc.container.overview.domain.User;
import java.util.Collection;


public class AnnotationQualifierDependencyInjectionDemo
{
    @Autowired()
    @Qualifier("user")
    private User user;

    @Autowired
    private User superUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;


    @Autowired
    @UserGroup
    private Collection<User> userGroup;

    @Autowired
    @Qualifier("nameUser")
    private Collection<User> nameUsers;


    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(AnnotationQualifierDependencyInjectionDemo.class);

        //注册XML配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        //启动上下文
        beanFactory.refresh();
        AnnotationQualifierDependencyInjectionDemo demo =
                beanFactory.getBean(AnnotationQualifierDependencyInjectionDemo.class);
        System.out.println(demo.user);
        System.out.println("------------------------------------------------------");
        System.out.println(demo.superUser);
        System.out.println("------------------------------------------------------");
        System.out.println(demo.allUsers);
        System.out.println("------------------------------------------------------");
        System.out.println(demo.qualifierUsers);
        System.out.println("------------------------------------------------------");
        System.out.println(demo.userGroup);
        System.out.println("------------------------------------------------------");
        System.out.println(demo.nameUsers);

        //关闭上下文
        beanFactory.close();
    }


    @Bean
    @Qualifier
    public User qualifierUser1()
    {
        User user = new User();
        user.setId(10L);
        user.setName("qualifierUser1");
        return user;
    }

    @Bean
    @Qualifier
    public User qualifierUser2()
    {
        User user = new User();
        user.setId(20L);
        user.setName("qualifierUser2");
        return user;
    }


    @Bean
    @UserGroup
    public User userGroupUser1()
    {
        User user = new User();
        user.setId(100L);
        user.setName("userGroupUser1");
        return user;
    }


    @Bean
    @UserGroup
    public User userGroupUser2()
    {
        User user = new User();
        user.setId(200L);
        user.setName("userGroupUser2");
        return user;
    }


    @Bean
    @Qualifier("nameUser")
    public User nameUser1()
    {
        User user = new User();
        user.setId(1000L);
        user.setName("nameUser1");
        return user;
    }

    @Bean
    @Qualifier("nameUser")
    public User nameUser2()
    {
        User user = new User();
        user.setId(2000L);
        user.setName("nameUser2");
        return user;
    }


}
