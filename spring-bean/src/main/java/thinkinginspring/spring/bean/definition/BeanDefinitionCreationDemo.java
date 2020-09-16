package thinkinginspring.spring.bean.definition;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import thinking.in.spring.ioc.container.overview.domain.User;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 示例
 */
public class BeanDefinitionCreationDemo
{
    public static void main(String[] args)
    {
        //BeanDefinitionBuilder 方式
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id",1)
                .addPropertyValue("name","ruby");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 通过AbstractBeanDefinition的派生类来实现
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues values = new MutablePropertyValues();
        values.add("id",1)
                .add("name","weiss");
        genericBeanDefinition.setPropertyValues(values);

    }

}
