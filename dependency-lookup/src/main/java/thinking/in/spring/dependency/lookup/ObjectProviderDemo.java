package thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


public class ObjectProviderDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(ObjectProviderDemo.class);
        beanFactory.refresh();
        demoObjectProvider(beanFactory);
        beanFactory.close();
    }

    @Bean
    public String helloWorld()
    {
        return "hello world";
    }

    private static void demoObjectProvider(AnnotationConfigApplicationContext beanFactory)
    {
        ObjectProvider<String> objectProvider = beanFactory.getBeanProvider(String.class);
        String text = objectProvider.getObject();
        System.out.println(text);
    }

}
