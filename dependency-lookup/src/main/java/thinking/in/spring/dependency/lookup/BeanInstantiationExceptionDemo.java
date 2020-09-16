package thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注册非具体类的时候会抛出{@link org.springframework.beans.BeanInstantiationException}
 */
public class BeanInstantiationExceptionDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(BeanInstantiationExceptionDemo.class);
        //Character是一个接口，是String的父类
        BeanDefinitionBuilder definitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(Character.class);
        beanFactory.registerBeanDefinition("cBean",definitionBuilder.getBeanDefinition());
        beanFactory.refresh();
        beanFactory.close();
    }


}
