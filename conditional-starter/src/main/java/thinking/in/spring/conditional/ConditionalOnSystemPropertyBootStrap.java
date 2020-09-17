package thinking.in.spring.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionalOnSystemPropertyBootStrap {
    static {
        System.setProperty("language","Chinese");
    }

    public static void main(String[] args) {
        //构建应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册配置类
        context.register(ConditionalMessageConfiguration.class);
        //刷选应用上下文
        context.refresh();
        //获取message Bean 并且打印
        String message = context.getBean("message", String.class);
        System.out.printf("message对象内容为%s\n",message);
        //关闭应用上下文
        context.close();
    }
}
