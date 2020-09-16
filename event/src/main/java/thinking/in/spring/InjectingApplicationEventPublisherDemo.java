package thinking.in.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.annotation.PostConstruct;

public class InjectingApplicationEventPublisherDemo implements
        ApplicationEventPublisherAware, ApplicationContextAware {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        // #3
        applicationEventPublisher
                .publishEvent(new MySpringEvent("@Autowired ApplicationEventPublisher publish event"));
        // #4
        applicationContext
                .publishEvent(new MySpringEvent("@Autowired applicationContext publish event"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingApplicationEventPublisherDemo.class);

        context.addApplicationListener(new MyApplicationEventListener());

        context.refresh();
        context.close();
    }

    //# 2
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext
                .publishEvent(new MySpringEvent("ApplicationContextAware applicationContext publish event"));
    }

    // #1
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher
                .publishEvent(new MySpringEvent("ApplicationEventPublisherAware ApplicationEventPublisher publish event"));
    }
}
