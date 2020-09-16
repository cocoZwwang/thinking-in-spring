package thinking.in.spring.ioc.bean.scope;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import thinking.in.spring.ioc.container.overview.domain.User;

public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        user.setName("cocoadel");
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.refresh();
        //单线程
        lookupByScopeInMainThread(applicationContext);
        //多线程
        System.out.println("==================================================================");
        lookupByScopeInMultiTread(applicationContext);
        applicationContext.close();
    }

    private static void lookupByScopeInMainThread(BeanFactory beanFactory) {
        for (int i = 0; i < 3; i++) {
            User user = beanFactory.getBean(User.class);
            System.out.printf("[Thread Id: %s] user = %s\n", Thread.currentThread().getId(), user);
        }
    }

    private static void lookupByScopeInMultiTread(BeanFactory beanFactory) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                User user = beanFactory.getBean(User.class);
                System.out.printf("[Thread Id: %s] user = %s\n", Thread.currentThread().getId(), user);
            }).start();
        }
    }
}
