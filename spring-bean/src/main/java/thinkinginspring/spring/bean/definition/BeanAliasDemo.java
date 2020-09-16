package thinkinginspring.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.resources.MsgAppletViewer_ja;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * BeanDefinitionAlias 实例
 */
public class BeanAliasDemo
{
    public static void main(String[] args)
    {
        String location = "classpath:META-INF/bean-definition-context.xml";
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(location);

        User rubyUser = beanFactory.getBean("ruby-user", User.class);
        User user = beanFactory.getBean("user", User.class);
    }


}
