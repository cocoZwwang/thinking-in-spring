package thinking.in.spring.annotation.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(HelloWordConfiguration.class)
public @interface EnableHelloWord {
}
