package thinking.in.spring.annotation.meta;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Set;
@Configuration
@TransactionalService
public class TransactionalServiceAnnotationMetadataBootstrap {
    public static void main(String[] args) throws IOException {
        String className = TransactionalServiceAnnotationMetadataBootstrap.class.getName();
        //创建MetadataReader工厂类
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        //获取MetadataReader对象
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(className);
        //获取AnnotationMetadata对象
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取直接标记在类上的注解，就是第一层注解
        Set<String> annotationSet = annotationMetadata.getAnnotationTypes();
        System.out.printf("直接注解集合：%s\n",annotationSet);
        //获取元注解
        annotationSet.forEach(annotationName ->{
            Set<String> metaAnnotationSet = annotationMetadata.getMetaAnnotationTypes(annotationName);
            metaAnnotationSet.forEach(metaName -> {
                System.out.printf("直接注解：%s，元注解：%s\n",annotationName,metaName);
            });
        });
    }
}
