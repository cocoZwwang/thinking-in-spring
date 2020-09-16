package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.dependency.domain.UserHolder;
import thinking.in.spring.ioc.container.overview.domain.User;

import javax.annotation.Resource;
import java.util.Map;


public class AnnotationDependencyFiledInjectionDemo {

    @Autowired
    private
//    static @Autowired 会忽略静态变量
            UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    @Autowired
    private Map<String, User> userMap;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(AnnotationDependencyFiledInjectionDemo.class);

        //注册XML配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        //启动上下文
        beanFactory.refresh();

        //依赖查找并且打印
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder.getUser());

        AnnotationDependencyFiledInjectionDemo demo = beanFactory.getBean(AnnotationDependencyFiledInjectionDemo.class);
        System.out.println("AnnotationDependencyFiledInjectionDemo.userHolder: " + demo.userHolder);
        System.out.println(userHolder == demo.userHolder);
        System.out.println(demo.userHolder2 == demo.userHolder);
        System.out.println("userMap:" + demo.userMap);


        //关闭上下文
        beanFactory.close();
    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

//    @Bean
//    public UserHolder autoUserHolder() {
//    }

}
