package thinking.in.spring.ioc.container.overview.repository;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.Collection;

public class UserRepository
{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //    private Collection<User> users;
//
//    private BeanFactory beanFactory;
//
//    private ObjectFactory<User> objectFactory;
//
//    private ObjectFactory<ApplicationContext> contextObjectFactory;
//
//
//    public Collection<User> getUsers()
//    {
//        return users;
//    }
//
//    public void setUsers(Collection<User> users)
//    {
//        this.users = users;
//    }
//
//    public BeanFactory getBeanFactory()
//    {
//        return beanFactory;
//    }
//
//    public void setBeanFactory(BeanFactory beanFactory)
//    {
//        this.beanFactory = beanFactory;
//    }
//
//    public ObjectFactory<User> getObjectFactory()
//    {
//        return objectFactory;
//    }
//
//    public void setObjectFactory(ObjectFactory<User> objectFactory)
//    {
//        this.objectFactory = objectFactory;
//    }
//
//    public ObjectFactory<ApplicationContext> getContextObjectFactory()
//    {
//        return contextObjectFactory;
//    }
//
//    public void setContextObjectFactory(ObjectFactory<ApplicationContext> contextObjectFactory)
//    {
//        this.contextObjectFactory = contextObjectFactory;
//    }
}
