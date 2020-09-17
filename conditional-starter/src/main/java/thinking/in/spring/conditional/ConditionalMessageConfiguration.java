package thinking.in.spring.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalMessageConfiguration {

    @Bean("message")
    @ConditionalOnSystemProperty(name = "language",value = "Chinese")
    public String messageChinese(){
        return  "你好，世界！";
    }

    @Bean("message")
    @ConditionalOnSystemProperty(name = "language",value = "English")
    public String messageEnglish(){
        return "hello world!";
    }
}
