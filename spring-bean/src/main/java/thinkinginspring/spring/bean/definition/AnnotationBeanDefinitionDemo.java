package thinkinginspring.spring.bean.definition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.Map;

/**
 * AnnotationBeanDefinition 实例
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        applicationContext.refresh();

        //命名注册
        registryBeanDefinition(applicationContext, "weiss-user");
        //非命名注册
        registryBeanDefinitionWithoutName(applicationContext);

        Map<String, Config> configs = applicationContext.getBeansOfType(Config.class);
        Map<String, User> users = applicationContext.getBeansOfType(User.class);

        //运行结果说明@Import和@Component不会导致Bean的重复注册
        System.out.println("Config Beans 集合：" + configs);
        System.out.println("User Beans 集合：" + users);

//        String str="Hello World";  //待判断的字符串
//        String reg=".*/ll/.*";  //判断字符串中是否含有ll
//        System.out.println(str.matches(reg));
    }

    private static void registryBeanDefinition(BeanDefinitionRegistry registry, String beanName)
    {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "weiss");
        if (StringUtils.hasText(beanName))
        {
            //命名的方式注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }
        else
        {
            //非命名的方式注册BeanDefinition
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    private static void registryBeanDefinitionWithoutName(BeanDefinitionRegistry registry)
    {
        registryBeanDefinition(registry, null);
    }


    @Component
    public static class Config
    {
        @Bean(name = {"user", "ruby-user"})
        public User user()
        {
            User user = new User();
            user.setId(1L);
            user.setName("ruby");
            return user;
        }
    }
}
