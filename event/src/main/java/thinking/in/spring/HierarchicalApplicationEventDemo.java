package thinking.in.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.HashSet;
import java.util.Set;

public class HierarchicalApplicationEventDemo {
    public static void main(String[] args) {
        //创建父应用上下文
        //注册事件监听
        AnnotationConfigApplicationContext parentContext =
                new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");
        parentContext.register(MyListener.class);

        //创建子应用上下文
        //注册事件监听
        AnnotationConfigApplicationContext currentContext =
                new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        currentContext.setParent(parentContext);
        currentContext.register(MyListener.class);


        parentContext.refresh();
        currentContext.refresh();
//
        currentContext.close();
        parentContext.close();
    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent>{

        private static Set<ApplicationContextEvent> set = new HashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if(set.add(event)){
                System.out.printf("接收Spring 应用上下文[id:%S]- 事件：%s\n",event.getApplicationContext().getId(),
                        event.getSource().getClass().getSimpleName());
            }

        }
    }
}
