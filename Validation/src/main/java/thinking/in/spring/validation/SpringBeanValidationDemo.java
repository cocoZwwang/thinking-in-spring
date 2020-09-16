package thinking.in.spring.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SpringBeanValidationDemo {
    public static void main(String[] args) {
        String location = "classpath:/MATE-INF/bean-validation-context.xml";
        ConfigurableApplicationContext context  = new ClassPathXmlApplicationContext(location);

//        LocalValidatorFactoryBean validatorFactoryBean = context.getBean(LocalValidatorFactoryBean.class);
//        System.out.println(validatorFactoryBean);
        UserProcessor userProcessor = context.getBean(UserProcessor.class);
        userProcessor.process(new User());

        context.close();
    }

    @Component
    @Validated
    static class UserProcessor{
        public void process(@Valid User user){
            System.out.println("user: " + user);
        }
    }

    static class User{
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
