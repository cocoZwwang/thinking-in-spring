package thinking.in.spring.annotation.enable;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * 通过 ImportSelector注册相应的Spring Bean
 */
public class ServerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String,Object> annotationAttributes =
                importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        return type == Server.Type.HTTP ?
                new String[]{HttpServer.class.getName()} :
                new String[]{FtpServer.class.getName()};
    }
}
