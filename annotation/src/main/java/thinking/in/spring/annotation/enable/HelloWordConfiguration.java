package thinking.in.spring.annotation.enable;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWordConfiguration {

    @Bean
    public String helloWorld(){
        return "hello! World";
    }
}
