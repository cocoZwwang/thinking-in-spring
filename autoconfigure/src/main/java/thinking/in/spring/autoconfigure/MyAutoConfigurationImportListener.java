package thinking.in.spring.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigurationImportEvent;
import org.springframework.boot.autoconfigure.AutoConfigurationImportListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Set;

public class MyAutoConfigurationImportListener implements AutoConfigurationImportListener {

    @Override
    public void onAutoConfigurationImportEvent(AutoConfigurationImportEvent event) {
        //获取当前classLoader
        ClassLoader classLoader = event.getClass().getClassLoader();
        //候选自动装配CLass名单
        List<String> candidates = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader);
        //实际的自动装配名单
        List<String> configurations = event.getCandidateConfigurations();
        //排除的自动装配名单
        Set<String> excludes = event.getExclusions();
        //输出各自的数量
        System.out.printf("候选自动配置类数量：%s,实际装配的数：%s，排除的数量：%s\n",
                candidates.size(),configurations.size(),excludes.size());
        //输出实际和排除的自动装配名单
        System.out.println("实际装配的自动配置类=============================");
        configurations.forEach(System.out::println);
        System.out.println("排除的自动装配类=================================");
        excludes.forEach(System.out::println);
        System.out.println("==============================================");
    }
}
