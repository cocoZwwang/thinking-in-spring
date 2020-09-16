package thinking.in.spring.ioc.bean.lifecycle;

import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        // TODO
        // 加载XML superUser
        // 通过 InstantiationAwareBeanPostProcessor   拦截superUser的创建 返回一个全新的实例

        // 增加一个UserHolder 演示有参构造方法实例化过程 （有参构造方法可以通过autowire来实现）

    }
}
