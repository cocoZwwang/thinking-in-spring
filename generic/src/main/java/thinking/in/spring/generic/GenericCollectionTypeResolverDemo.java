package thinking.in.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericCollectionTypeResolverDemo {

    private StringList stringList;

    private List<String>  strings;

    private Map<String,Integer> map;

    private Set set;

    public static void main(String[] args) throws NoSuchFieldException {

        //StringList -> ArrayList<String> 具体化
        //getCollectionType返回具体化泛型参数类型集合的成员类型
        //返回 class java.lang.String
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));

        //非具体化的泛型类型
        //返回空
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        //获取属性 具体化泛型参数类型
        //返回class java.lang.String
        Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

        //返回class java.lang.String
        field = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

        //返回
        //key : class java.lang.String
        //value: class java.lang.Integer
        field = GenericCollectionTypeResolverDemo.class.getDeclaredField("map");
        System.out.println("key :" + GenericCollectionTypeResolver.getMapKeyFieldType(field));
        System.out.println("value:" + GenericCollectionTypeResolver.getMapValueFieldType(field));

        //泛型 非具体化参数类型
        //返回null
        field = GenericCollectionTypeResolverDemo.class.getDeclaredField("set");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
    }
}
