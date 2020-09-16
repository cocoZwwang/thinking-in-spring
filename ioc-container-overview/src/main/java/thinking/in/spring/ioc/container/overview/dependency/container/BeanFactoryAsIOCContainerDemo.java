package thinking.in.spring.ioc.container.overview.dependency.container;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.Map;

public class BeanFactoryAsIOCContainerDemo
{
    public static void main(String[] args)
    {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-injection-context.xml";
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("bean 加载的数量：" + beanDefinitionCount);

        lookupInCollection(beanFactory);
    }

    private static void lookupInCollection(BeanFactory beanFactory)
    {
        if (beanFactory instanceof ListableBeanFactory)
        {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("根据类型集合查找：" + users);
        }
    }
}
