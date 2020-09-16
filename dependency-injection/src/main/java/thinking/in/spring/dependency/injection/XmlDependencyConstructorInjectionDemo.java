package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import thinking.in.spring.dependency.domain.UserHolder;

public class XmlDependencyConstructorInjectionDemo
{


    public static void main(String[] args)
    {
        //创建IOC容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //读取外部化配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/xml-dependency-constructor-injection.xml";
        reader.loadBeanDefinitions(location);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder.getUser());
    }
}
