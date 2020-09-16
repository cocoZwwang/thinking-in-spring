package thinking.in.spring.annotation.meta;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;

/**
 * 基于spring {@link org.springframework.core.type.StandardAnnotationMetadata} 获取注解信息
 * StandardAnnotationMetadata 是基于Java反射机制
 */
@TransactionalService(name = "cocoAdel")
public class TransactionalServiceStandardAnnotationMetadataBootstrap {

    public static void main(String[] args) {
        AnnotationMetadata annotationMetadata =
                //spring framework 5.2之后
                AnnotationMetadata.introspect(TransactionalServiceStandardAnnotationMetadataBootstrap.class);
                //spring framework 5.2之前
//                new StandardAnnotationMetadata(TransactionalServiceStandardAnnotationMetadataBootstrap.class,false);
        //获取直接注解
        Set<String> annotationNames = annotationMetadata.getAnnotationTypes();
        System.out.printf("直接注解集合：%s\n",annotationNames);

        //获取元注解
        annotationNames.forEach(annotationName ->{
            Set<String> metaAnnotationSet = annotationMetadata.getMetaAnnotationTypes(annotationName);
            metaAnnotationSet.forEach(metaName -> {
                System.out.printf("直接注解：%s，元注解：%s\n",annotationName,metaName);
                Map<String,Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(metaName);
                if(!CollectionUtils.isEmpty(annotationAttributes)){
                    annotationAttributes.forEach((name,value) -> {
                        System.out.printf("元注解 %s 属性 %s = %s \n", ClassUtils.getShortName(metaName),name,value);
                    });
                }
                System.out.println("================================================");
            });
        });
    }
}
