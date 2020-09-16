package thinking.in.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Spring 事件
 * 基于注解来实现 同步 -》 异步切换
 */
@EnableAsync //激活异步模式
public class AnnotatedAsyncEventHandlerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedAsyncEventHandlerDemo.class);
        context.refresh();
        context.publishEvent(new MySpringEvent("hello world!"));
        context.close();
    }

    @EventListener
    @Async
    public void onAsyncMySpringEvent(MySpringEvent event){
        System.out.printf("[Thread:%s]-@EventListener-@Async-接收到Spring事件 - %s\n",
                Thread.currentThread().getName(), event.getMessage());
    }

    @Bean
    public Executor taskExecutor(){
        return Executors.newSingleThreadExecutor(
                new CustomizableThreadFactory("My-Spring-event-executor-a"));
    }
}
