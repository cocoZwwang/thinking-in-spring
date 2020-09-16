package thinking.in.spring.ioc.container.overview.domain;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import thinking.in.spring.ioc.container.overview.enums.City;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class User implements InitializingBean, Destroyable
{
    private Long id;

    private String name;

    private Company company;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

//    private Map<String, City> cityMap;

    private Resource resource;

    private Properties context;

    private String contextAsText;

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    public Resource getResource()
    {
        return resource;
    }

    public void setResource(Resource resource)
    {
        this.resource = resource;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public City[] getWorkCities()
    {
        return workCities;
    }

    public void setWorkCities(City[] workCities)
    {
        this.workCities = workCities;
    }

    public List<City> getLifeCities()
    {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities)
    {
        this.lifeCities = lifeCities;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    public String getContextAsText() {
        return contextAsText;
    }

    public void setContextAsText(String contextAsText) {
        this.contextAsText = contextAsText;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", resource=" + resource +
                ", context=" + context +
                ", contextAsText='" + contextAsText + '\'' +
                '}';
    }

    public static User createUser()
    {
        User user = new User();
        user.setId(1L);
        user.setName("ruby");
        return user;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
//        System.out.println("initializingBean#afterPropertiesSet");
    }

//    public Map<String, City> getCityMap()
//    {
//        return cityMap;
//    }
//
//    public void setCityMap(Map<String, City> cityMap)
//    {
//        this.cityMap = cityMap;
//    }


    @Override
    public void destroy() throws DestroyFailedException {
        System.out.println("正在销毁对象： " + toString());
    }
}