package thinking.in.spring.ioc.container.overview.factory;

import thinking.in.spring.ioc.container.overview.domain.User;

public interface UserFactory
{
    default User createUser()
    {
        return User.createUser();
    }
}
