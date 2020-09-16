package thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class GenericAPIDemo {

    public static void main(String[] args) {
        //原生类型
        Class intClass = int.class;

        //数组类型
        Class objectArrayClass = Object[].class;

        //原始类型
        Class rawClass = String.class;

        //泛型参数类型
        ParameterizedType parameterizedType =
                (ParameterizedType) ArrayList.class.getGenericSuperclass();

        Class arrayListClass = (Class) parameterizedType.getRawType();

        System.out.println(arrayListClass);

        //泛型类型变量
        Type[] types =  parameterizedType.getActualTypeArguments();
        Stream.of(types)
                .map(TypeVariable.class::cast)
                .forEach(System.out::println);
    }
}
