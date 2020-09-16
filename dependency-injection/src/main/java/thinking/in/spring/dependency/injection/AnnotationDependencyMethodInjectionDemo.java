package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.dependency.domain.UserHolder;
import thinking.in.spring.ioc.container.overview.domain.User;

import javax.annotation.Resource;


public class AnnotationDependencyMethodInjectionDemo
{
    private UserHolder userHolder1;

    private UserHolder userHolder2;

    @Autowired
    private void initUserHolder1(UserHolder userHolder)
    {
        this.userHolder1 = userHolder;
    }


    @Resource
    private void initUserHolder2(UserHolder userHolder)
    {
        this.userHolder2 = userHolder;
    }


    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(AnnotationDependencyMethodInjectionDemo.class);

        //注册XML配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        //启动上下文
        beanFactory.refresh();

        //依赖查找并且打印
//        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
//        System.out.println(userHolder.getUser());

        AnnotationDependencyMethodInjectionDemo demo = beanFactory.getBean(AnnotationDependencyMethodInjectionDemo.class);
        System.out.println(demo.userHolder1);
        System.out.println(demo.userHolder2);

        //关闭上下文
        beanFactory.close();
    }

    @Bean
    public UserHolder userHolder(User user)
    {
        return new UserHolder(user);
    }

}
