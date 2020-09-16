package thinking.in.spring.ioc.container.overview.factory.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import thinking.in.spring.ioc.container.overview.factory.UserFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.DestroyFailedException;


public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    public DefaultUserFactory() {
        System.out.println("调用构造函数实例化。。。");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct初始化。。。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean初始化。。。");
    }


    public void initByAnnotation() {
        System.out.println("自定义初始化。。。。。");
    }


    @PreDestroy
    public void onPerDestroy() {
        System.out.println("PreDestroy销毁回调。。。");
    }


    public void destroyByAnnotation() {
        System.out.println("自定义销毁回调。。。。。");
    }


    @Override
    public void destroy() throws DestroyFailedException {
        System.out.println("DisposableBean销毁回调。。。。。");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("垃圾回收调用finalize方法。。。");
    }
}
