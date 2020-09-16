package thinking.in.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Spring 事件
 * 基于实现类 同步-》异步模式切换
 */
public class AsyncEventHandlerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AsyncEventHandlerDemo.class);
        context.addApplicationListener(new MyApplicationEventListener());
        context.refresh();

        //依赖查找ApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster =
                context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME,
                        ApplicationEventMulticaster.class);
        //强制转换为SimpleApplicationEventMulitcaster
        if(applicationEventMulticaster instanceof SimpleApplicationEventMulticaster){
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster =
                    (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            //创建线程池
            ExecutorService taskExecutor = Executors.newSingleThreadExecutor(
                    new CustomizableThreadFactory("My-Spring-event-executor-a"));
            //设置线程池
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);
            //监听上下文关闭事件，注销线程池，否则上下文关闭后，程序无法正常关闭
            context.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> {
                if(!taskExecutor.isShutdown()){
                    taskExecutor.shutdown();
                }
            });
            //Spring 事件异常处理
            simpleApplicationEventMulticaster.setErrorHandler(e ->{
                System.out.printf("Spring 事件广播失败-原因：%s\n",e.getMessage());
                System.out.println();
            });

        }

        context.addApplicationListener((ApplicationListener<MySpringEvent>) event -> {
            throw new RuntimeException("我是故意的！");
        });


        context.publishEvent(new MySpringEvent("hello world!"));
        context.publishEvent(new MySpringEvent2("hello world! - 2"));

        context.close();
    }

}
