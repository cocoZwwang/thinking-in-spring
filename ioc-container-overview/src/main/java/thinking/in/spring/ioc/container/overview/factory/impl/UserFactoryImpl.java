package thinking.in.spring.ioc.container.overview.factory.impl;

import thinking.in.spring.ioc.container.overview.domain.User;
import thinking.in.spring.ioc.container.overview.factory.UserFactory;

public class UserFactoryImpl implements UserFactory
{
    @Override
    public User createUser()
    {
        User user = new User();
        user.setId(2L);
        user.setName("weiss");

        return user;
    }
}
