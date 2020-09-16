package thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.util.List;

public class GenericTypeResolveDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        disPlayGenericType("getString",GenericTypeResolveDemo.class,List.class);

        disPlayGenericType("getList",GenericTypeResolveDemo.class,List.class);

        disPlayGenericType("getStringList",GenericTypeResolveDemo.class,List.class);

        disPlayGenericType("getIntegerList",GenericTypeResolveDemo.class,List.class);

    }


    public static <E> List<E> getList(){// 参数类型没有具体化
        return null;
    }

    public static StringList getStringList(){//参数类型具体化
        return null;
    }

    public static List<Integer> getIntegerList(){// 参数类型具体化
        return  null;
    }

    public static String getString(){
        return null;
    }

    private static void disPlayGenericType(String methodName,Class<?> containsClazz,Class<?> genericIfc,
                                           Class... argumentTypes)
            throws NoSuchMethodException {
        Method method = containsClazz.getMethod(methodName, argumentTypes);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method,containsClazz);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s)=%s\n",
                "getString",containsClazz,returnType);

        Class<?> returnTypeArg = GenericTypeResolver.resolveReturnTypeArgument(method,genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s)=%s\n",
                "getString",genericIfc,returnTypeArg);

        System.out.println("======================================================================");
    }

}


