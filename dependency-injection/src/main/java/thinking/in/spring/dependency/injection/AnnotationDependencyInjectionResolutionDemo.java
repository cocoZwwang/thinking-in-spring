package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import thinking.in.spring.dependency.annotation.InjectUser;
import thinking.in.spring.dependency.annotation.MyAutowired;
import thinking.in.spring.ioc.container.overview.domain.User;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;


public class AnnotationDependencyInjectionResolutionDemo
{
    @Autowired
    private User user;

    @MyAutowired
    private Optional<User> userOptional;


    @Inject
    private User injectUser;

    @InjectUser
    private User myInjectUser;

//    @Bean(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor()
//    {
//        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> set =
//                new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectUser.class));
//        postProcessor.setAutowiredAnnotationTypes(set);
//        return postProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor()
    {
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        postProcessor.setAutowiredAnnotationType(InjectUser.class);
        return postProcessor;
    }

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(AnnotationDependencyInjectionResolutionDemo.class);
        //注册XML配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        beanFactory.refresh();
        AnnotationDependencyInjectionResolutionDemo demo =
                beanFactory.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println(demo.user);
        System.out.println(demo.userOptional.get());
        System.out.println(demo.injectUser);
        System.out.println(demo.myInjectUser);
        beanFactory.close();
    }

}
