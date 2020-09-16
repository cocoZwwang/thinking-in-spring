package thinking.in.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public class ApplicationListenerDemo implements ApplicationContextAware {

    public static void main(String[] args) {
//        GenericApplicationContext context = new GenericApplicationContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);

        //基于接口 注册
        // a、使用ConfigurableApplicationContext API
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println(event);
            }
        });
        //b注册Spring Bean的方式
        context.register(MyApplicationListener.class);

        context.refresh();
        context.start();
        context.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.publishEvent(new ApplicationEvent("hello world") {
        });
        //会发布一个PayloadApplicationEvent事件
        applicationContext.publishEvent("hello world");
    }

    static class  MYPayloadApplicationEvent extends PayloadApplicationEvent<String>{

        public MYPayloadApplicationEvent(Object source, String payload) {
            super(source, payload);
        }
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            print("Spring Bean-接收事件-ContextRefreshedEvent");
        }
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent(ContextStartedEvent event) {
        print("@EventListener-接收事件-ContextStartedEvent");
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent1(ContextStartedEvent event) {
        print("@EventListener-接收事件-ContextStartedEvent1");
    }

    @Async
    @EventListener
    public void onApplicationEventAsync(ContextStartedEvent event) {
        print("@EventListener-Async-接收事件-ContextStartedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        print("@EventListener-接收事件-ContextRefreshedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextStoppedEvent event) {
        print("@EventListener-接收事件-ContextStoppedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        print("@EventListener-接收事件-ContextClosedEvent");
    }

    public static void print(Object printable){
        System.out.printf("[Thread: %s] - %s\n",Thread.currentThread().getName(),printable);
    }
}
