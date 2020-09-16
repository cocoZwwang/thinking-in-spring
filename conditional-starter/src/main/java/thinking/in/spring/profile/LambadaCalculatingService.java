package thinking.in.spring.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

@Profile("Java8")
@Service
public class LambadaCalculatingService implements CalculatingService{
    @Override
    public Integer sum(Integer... values) {
        int sum = Stream.of(values).reduce(0,Integer::sum);
        System.out.printf("[java 8 lambada 实现]%s累加结果：%s\n", Arrays.asList(values),sum);
        return sum;
    }
}
