package thinking.in.spring.conversion;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.container.overview.domain.User;

public class CustomizedPropertyEditorRegistrarDemo {

    public static void main(String[] args) {
        String location = "classpath:/META-INF/conversion-bean-context.xml";
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(location);
        User user = context.getBean(User.class);
        System.out.println(user);
        context.close();

    }


}
