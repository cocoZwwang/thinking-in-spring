package thinking.in.spring.ioc.container.overview.factory;

import org.springframework.beans.factory.FactoryBean;
import thinking.in.spring.ioc.container.overview.domain.User;

public class UserFactoryBean implements FactoryBean<User>
{
    @Override
    public User getObject() throws Exception
    {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType()
    {
        return User.class;
    }
}
