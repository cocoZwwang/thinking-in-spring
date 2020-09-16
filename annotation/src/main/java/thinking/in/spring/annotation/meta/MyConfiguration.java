package thinking.in.spring.annotation.meta;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConfiguration {
    /**
     * 名称属性，不允许为空
     */
    String name();
}
