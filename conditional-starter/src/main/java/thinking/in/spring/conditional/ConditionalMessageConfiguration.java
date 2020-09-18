package thinking.in.spring.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 根据系统属性language的值来注册相应的message bean
 */
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
