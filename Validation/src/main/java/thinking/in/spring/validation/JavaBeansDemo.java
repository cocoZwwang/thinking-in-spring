package thinking.in.spring.validation;

import sun.nio.cs.StreamEncoder;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * Java Beans 示例
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
//        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        //stopClass 排除（截止类）
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(System.out::println);
        System.out.println("============================================");
        Stream.of(beanInfo.getMethodDescriptors())
                .forEach(System.out::println);

    }
}
