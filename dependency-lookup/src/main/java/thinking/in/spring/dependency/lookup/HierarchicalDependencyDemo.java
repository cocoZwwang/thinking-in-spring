package thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HierarchicalDependencyDemo
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyDemo.class);
        applicationContext.refresh();
        ConfigurableBeanFactory configurableBeanFactory = applicationContext.getBeanFactory();
        // HierarchicalBeanFactory<-ConfigurableBeanFactory<-ConfigurableListableBeanFactory
//        System.out.println("beanFactory 父BeanFactory： " + configurableBeanFactory.getParentBeanFactory());
        BeanFactory beanFactory = createParentBeanFactory();
        configurableBeanFactory.setParentBeanFactory(beanFactory);

        HierarchicalBeanFactory parentBeanFactory = (HierarchicalBeanFactory) configurableBeanFactory.getParentBeanFactory();
        System.out.println("beanFactory == parentBeanFactory: " + (beanFactory == parentBeanFactory));

        displayContainerLocalBean(configurableBeanFactory, "user");
        displayContainerLocalBean(parentBeanFactory, "user");
        System.out.println("======================================================");
        displayContainerBean(configurableBeanFactory, "user");
        displayContainerBean(parentBeanFactory, "user");
        System.out.println("======================================================");
        containerBean(configurableBeanFactory, "user");
        System.out.printf("当前%s是否包含Bean:%s ----- %s\n", configurableBeanFactory, "user",
                containerBean(configurableBeanFactory, "user"));
        System.out.printf("当前%s是否包含Bean:%s ----- %s\n", parentBeanFactory, "user",
                containerBean(parentBeanFactory, "user"));

        //这里可以发现虽然通过
        System.out.println("当前BeanFactory user localBean：" + configurableBeanFactory.getBean("user"));
        System.out.println("委派BeanFactory user Bean：" + parentBeanFactory.getBean("user"));

        applicationContext.close();
    }

    //递归实现containsBean方法
    private static boolean containerBean(HierarchicalBeanFactory beanFactory, String beanName)
    {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory)
        {
            if (containerBean((HierarchicalBeanFactory) parentBeanFactory, beanName))
            {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainerLocalBean(HierarchicalBeanFactory beanFactory, String beanName)
    {
        System.out.printf("当前%s是否包含LocalBean:%s ----- %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    private static void displayContainerBean(HierarchicalBeanFactory beanFactory, String beanName)
    {
        System.out.printf("当前%s是否包含Bean:%s ----- %s\n", beanFactory, beanName,
                beanFactory.containsBean(beanName));
    }


    private static BeanFactory createParentBeanFactory()
    {
        String location = "classpath:META-INF/dependency-lookup-context.xml";
        return new ClassPathXmlApplicationContext(location);
    }
}
