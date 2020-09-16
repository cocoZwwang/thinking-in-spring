package thinking.in.spring.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AwareDependencyInjectionDemo implements BeanFactoryAware
{
    private BeanFactory beanFactory;

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AwareDependencyInjectionDemo.class);

        //启动上下文
        applicationContext.refresh();
        AwareDependencyInjectionDemo demo = applicationContext.getBean(AwareDependencyInjectionDemo.class);
        System.out.println(demo.beanFactory == applicationContext.getBeanFactory());

        //关闭上下文
        applicationContext.close();
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        this.beanFactory = beanFactory;
    }
}
