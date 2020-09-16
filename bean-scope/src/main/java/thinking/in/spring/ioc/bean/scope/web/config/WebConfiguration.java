package thinking.in.spring.ioc.bean.scope.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import thinking.in.spring.ioc.container.overview.domain.User;

@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
//    @RequestScope
//    @SessionScope
    @ApplicationScope
    public User user() {
        User user = new User();
        long nanoTime = System.nanoTime();
        user.setId(nanoTime);
        user.setName("CocoAdel");
        return user;
    }
}
