package thinking.in.spring.web.mvc.starter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackageClasses = SpringWebApplicationInitializerBootStrap.class)
@Configuration
public class SpringWebApplicationInitializerBootStrap {

}
