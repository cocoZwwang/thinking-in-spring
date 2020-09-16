package thinking.in.spring.profile;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Profile("Java7")
@Service
public class IterationCalculatingService implements CalculatingService{
    @Override
    public Integer sum(Integer... values) {
        int sum = 0;
        for(Integer v: values){
            sum += v;
        }
        System.out.printf("[java 7 迭代 实现]%s累加结果：%s\n", Arrays.asList(values),sum);
        return sum;
    }
}
