package thinking.in.spring.annotation.meta;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyComponent2
@MyConfiguration(name = "my application")
public @interface MyApplication {

}
