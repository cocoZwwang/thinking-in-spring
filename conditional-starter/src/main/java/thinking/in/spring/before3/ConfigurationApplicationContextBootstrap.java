package thinking.in.spring.before3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过外部化配置和桥接XML上下文的方式实现有序的条件配置
 * 这种方式各个条件之间是互斥的，并且必须一定要有一个条件配置，不能为空。
 */
public class ConfigurationApplicationContextBootstrap {
    static {
        // 调整env系统属性，实现“name” bean的不同配置下的切换
        // envValue 可能来自 “-D”命令行的参数
        // 参数不存在的时候，默认使用”prod“
        String envValue = System.getProperty("env","prod");
        System.setProperty("env",envValue);
    }

    public static void main(String[] args) {
        //定义XML 应用上下文
        String location = "classpath:/META-INF/configuration-context.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(location);
        //获取名称为”name“ 的Bean
        String value = context.getBean("name",String.class);
        // 内容输出
        System.out.println("Bean name 的内容为：" + value);
        context.close();
    }
}
