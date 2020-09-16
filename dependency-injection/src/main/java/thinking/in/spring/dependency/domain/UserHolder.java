package thinking.in.spring.dependency.domain;

import thinking.in.spring.ioc.container.overview.domain.User;

public class UserHolder
{
    private User user;


    public UserHolder(User user)
    {
        this.user = user;
    }

    public UserHolder()
    {

    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
