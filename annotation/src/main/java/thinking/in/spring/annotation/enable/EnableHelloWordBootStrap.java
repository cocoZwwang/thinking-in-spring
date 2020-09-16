package thinking.in.spring.annotation.enable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于"注解驱动"的Enable模块编程
 */
@EnableHelloWord
public class EnableHelloWordBootStrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        //注册引导类
        context.register(EnableHelloWordBootStrap.class);
        context.refresh();
        //获取Spring bean Hello world
        String helloWorld = context.getBean("helloWorld",String.class);
        System.out.println("hello world = " + helloWorld);
        context.close();
    }
}
