package thinking.in.spring.annotation.meta;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "thinking.in.spring.annotation") // class paths
public class ComponentScanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ComponentScanDemo.class);

        context.refresh();
        //@MyComponent2 <- @MyComponent <- @Component
        //从Spring framework 4.0开始支持@Component多层次“派生”
        System.out.println(context.getBean(TestClass.class));
        context.close();
    }
}
