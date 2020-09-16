package thinking.in.spring.annotation.meta;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyComponent
public @interface MyComponent2 {
}
