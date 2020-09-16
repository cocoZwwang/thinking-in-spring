package thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * bean初始化错误的时候就会抛出{@link BeanCreationExceptionDemo}
 */
public class BeanCreationExceptionDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(BeanCreationExceptionDemo.class);
        beanFactory.refresh();
        beanFactory.getBean(POJO.class);
        beanFactory.close();
    }

    @Bean
    public POJO jojo()
    {
        return new POJO();
    }

    private static class POJO implements InitializingBean
    {
        //这里PostConstruct初始化顺序在InitializingBean接口之前，所以这里抛出异常，
        // 后面的afterProperties方法旧不会执行了
        @PostConstruct
        public void init()
        {
            throw new RuntimeException("PostConstruct init error");
        }

        @Override
        public void afterPropertiesSet() throws Exception
        {
            throw new RuntimeException("afterPropertiesSet error");
        }
    }
}
