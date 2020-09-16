package thinking.in.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class DependencySourceDemo
{

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @PostConstruct
    public void init()
    {
        System.out.println("applicationContext == beanFactory :" +
                (applicationContext == beanFactory));
        System.out.println("applicationContext == applicationContext.getAutowireCapableBeanFactory() :" +
                (beanFactory == applicationContext.getAutowireCapableBeanFactory()));

        System.out.println("applicationContext == resourceLoader :" +
                (applicationContext == resourceLoader));

        System.out.println("applicationContext == applicationEventPublisher :" +
                (applicationContext == applicationEventPublisher));
    }

    @PostConstruct
    public void lookup()
    {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> tClass)
    {
        try
        {
            return applicationContext.getBean(tClass);
        }
        catch (Exception e)
        {
            System.err.println("无法查找类型" + tClass.getTypeName() + " Bean!");
        }
        return null;
    }


    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(DependencySourceDemo.class);
        //启动上下文
        annotationConfigApplicationContext.refresh();
        DependencySourceDemo demo = annotationConfigApplicationContext.getBean(DependencySourceDemo.class);


        //关闭上下文
        annotationConfigApplicationContext.close();
    }
}
