package thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

public class ResolvableTypeDemo {

    public static void main(String[] args) {

        //StringList <- ArrayList<String> <- AbstractList<String> <- List <- Collection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        System.out.println(resolvableType.getSuperType()); // ArrayList
        System.out.println(resolvableType.getSuperType().getSuperType()); // AbstractList

        System.out.println(resolvableType.asCollection().resolve()); // RowType
        System.out.println(resolvableType.asCollection().getGeneric(0));//获取泛型参数类型
        /**
         * {@link ResolvableType#toString()} 如果resolved为null，返回 ?
         */
        System.out.println(resolvableType.asCollection().getGeneric(1));//没有第二个泛型参数返回'?'
    }
}
