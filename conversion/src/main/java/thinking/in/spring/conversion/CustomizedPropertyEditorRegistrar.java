package thinking.in.spring.conversion;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import thinking.in.spring.ioc.container.overview.domain.User;

public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        //1.通用实现
        //2.具体的Java Bean属性实现
        registry.registerCustomEditor(User.class,
                "context", new StringToPropertiesPropertyEditor());
    }
}
