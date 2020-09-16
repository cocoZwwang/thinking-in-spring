package thinking.in.spring.annotation.meta;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.AnnotatedElement;

/**
 * 通过 {@link org.springframework.core.annotation.AnnotatedElementUtils} 获取单个注解的属性
 */
@TransactionalService
public class AnnotationAttributesBootStrap {

    public static void main(String[] args) {
        AnnotatedElement annotatedElement = AnnotationAttributesBootStrap.class;
        //获取@TransactionalService的元注解@Service的属性
        AnnotationAttributes serviceAttributes = AnnotatedElementUtils
                .getMergedAnnotationAttributes(annotatedElement, Service.class);
        //获取@TransactionalService的元注解@Transactional的属性
        AnnotationAttributes transactionalAttributes = AnnotatedElementUtils
                .getMergedAnnotationAttributes(annotatedElement, Transactional.class);
        //打印属性
        printAnnotationAttributes(serviceAttributes);
        System.out.println("==================================");
        printAnnotationAttributes(transactionalAttributes);
    }

    private static void printAnnotationAttributes(AnnotationAttributes annotationAttributes){
        System.out.printf("注解@%s 属性集合：\n",annotationAttributes.annotationType().getName());
        annotationAttributes.forEach((name,value) -> {
            System.out.printf("\t属性 %s : %s \n",name,value);
        });
    }
}
