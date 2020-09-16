package thinking.in.spring.annotation.enable;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootStrap {

    public static void main(String[] args) {
        //创建注解处理的应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册引导类
        context.register(EnableServerBootStrap.class);
        //刷新BeanFactory
        context.refresh();
        //获取Server Bean
        Server  server = context.getBean(Server.class);
        //测试Bean用例
        server.start();
        server.stop();
        //关闭应用上下文
        context.close();
    }
}
