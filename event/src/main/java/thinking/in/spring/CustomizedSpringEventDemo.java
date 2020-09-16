package thinking.in.spring;

import org.springframework.context.support.GenericApplicationContext;

public class CustomizedSpringEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new MyApplicationEventListener());

        context.refresh();

        context.publishEvent(new MySpringEvent("hello world!"));

        context.close();
    }
}
