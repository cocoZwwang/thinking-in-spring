package thinking.in.spring;

import org.springframework.context.ApplicationListener;

public class MyApplicationEventListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[Thread:%s]接收到Spring事件 - %s\n",Thread.currentThread().getName(),
                event.getMessage());
    }
}
