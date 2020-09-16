package thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class NoUniqueBeanDefinitionExceptionDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(NoUniqueBeanDefinitionExceptionDemo.class);
        beanFactory.refresh();
        //单一查找获取不唯一Bean对象
        try
        {
            //这是非类型安全查找方法
            beanFactory.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e)
        {
            System.out.printf("你要查询：%s类型的Bean对象有%s个,发生错误：%s",
                    String.class, e.getNumberOfBeansFound(), e.getMessage());
        }
        beanFactory.close();
    }

    @Bean
    public String bean1()
    {
        return "bean1";
    }

    @Bean
    String bean2()
    {
        return "bean2";
    }

}
