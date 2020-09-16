package thinking.in.spring.annotation.meta;

import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * 通过Java标准接口 {@link AnnotatedElement} 获取注解信息
 */
@TransactionalService(name = "ruby")
public class TransactionalServiceAnnotatedElementBootstrap {
    public static void main(String[] args) {
        //class实现了AnnotatedElement接口
        AnnotatedElement annotatedElement = TransactionalServiceAnnotatedElementBootstrap.class;
        //获取TransactionalService注解Annotation对象
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);

        //显式调用注解属性方法
//        String name = transactionalService.name();
//        System.out.printf("transactionalService.name=%s\n",name);

        //完全Java反射(ReflectionUtils是Spring反射工具类)
//        ReflectionUtils.doWithMethods(TransactionalService.class,
//                method -> {
//                    System.out.printf("TransactionalService.%s()=%s\n",method.getName(),
//                            ReflectionUtils.invokeMethod(method,transactionalService));
//                },
//                method -> method.getParameterCount() == 0);

        //完全Java反射，并且排除Annotation接口方法
        ReflectionUtils.doWithMethods(TransactionalService.class,
                method -> {
                    System.out.printf("TransactionalService.%s()=%s\n",method.getName(),
                            ReflectionUtils.invokeMethod(method,transactionalService));
                },
                method -> !method.getDeclaringClass().equals(Annotation.class) &&
                        method.getParameterCount() == 0);
    }
}
