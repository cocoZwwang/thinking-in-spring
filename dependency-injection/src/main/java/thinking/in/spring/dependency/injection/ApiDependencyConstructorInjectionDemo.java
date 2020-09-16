package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import thinking.in.spring.dependency.domain.UserHolder;


public class ApiDependencyConstructorInjectionDemo
{
    public static void main(String[] args)
    {
        //创建IOC容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //读取外部化配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        //通过API注册BeanDefinition
        beanFactory.registerBeanDefinition("userHolder", createUserHolderBeanDefinition());

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder.getUser());
    }

    private static BeanDefinition createUserHolderBeanDefinition()
    {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        beanDefinitionBuilder.addConstructorArgReference("superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }
}
