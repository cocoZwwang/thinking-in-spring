package thinking.in.spring.annotation.meta;

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.Set;

/**
 * 1.通过Java标准接口 {@link AnnotatedElement} 获取注解信息
 * 2.递归获取所有的元注解信息
 */
@TransactionalService(name = "ruby")
public class TransactionalServiceAnnotatedElementBootstrap2 {
    public static void main(String[] args) {
        //class实现了AnnotatedElement接口
        AnnotatedElement annotatedElement = TransactionalServiceAnnotatedElementBootstrap2.class;
        //获取TransactionalService注解Annotation对象
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);
        Set<Annotation> annotationSet = new HashSet<>();
        getAllMetaAnnotation(transactionalService,annotationSet);
        annotationSet.forEach(TransactionalServiceAnnotatedElementBootstrap2::printAnnotation);
    }

    private static void getAllMetaAnnotation(Annotation annotation,Set<Annotation> visited){
        Annotation[] annotations = annotation.annotationType().getAnnotations();
        if(ObjectUtils.isEmpty(annotations)){
            return;
        }

        for(Annotation metaAnnotation : annotations){
            if (!visited.contains(metaAnnotation)) {
                visited.add(metaAnnotation);
                getAllMetaAnnotation(metaAnnotation,visited);
            }
        }
    }

    private static void printAnnotation(Annotation annotation){
        Class<?> clazz = annotation.annotationType();
        //完全Java反射，并且排除Annotation接口方法
        ReflectionUtils.doWithMethods(clazz,
                method -> {
                    System.out.printf("%s.%s()=%s\n",clazz.getSimpleName(),method.getName(),
                            ReflectionUtils.invokeMethod(method,annotation));
                },
                method -> !method.getDeclaringClass().equals(Annotation.class) &&
                        method.getParameterCount() == 0);
    }
}
