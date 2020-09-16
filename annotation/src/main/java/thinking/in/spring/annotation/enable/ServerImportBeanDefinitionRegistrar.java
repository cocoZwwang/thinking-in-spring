package thinking.in.spring.annotation.enable;


import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * 通过ImportBeanDefinitionRegistrar注册spring bean
 * 相对于 {@link org.springframework.context.annotation.ImportSelector}
 * ImportBeanDefinitionRegistrar更加自由，可以自定义BeanDefinition的注册
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String,Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(EnableServer.class.getName());
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        //这里可以注册一个或者多个类
        String className = type == Server.Type.HTTP ? HttpServer.class.getName() : FtpServer.class.getName();
        //注册Spring Bean
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(className);
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition,registry);
    }
}
