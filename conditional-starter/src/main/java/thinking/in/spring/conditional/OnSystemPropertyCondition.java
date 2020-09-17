package thinking.in.spring.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

/**
 * 自定义系统属性匹配接口实现
 */
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> annotationAttributes =  metadata.getAllAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        String name = (String) annotationAttributes.getFirst("name");
        String value = (String) annotationAttributes.getFirst("value");

        String systemValue = System.getProperty(name);
        if(Objects.equals(value,systemValue)){
            System.out.printf("系统属性[%s:%s]匹配成功\n",name,value);
            return true;
        }

        return false;
    }
}
