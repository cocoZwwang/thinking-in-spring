package thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GenericDemo {
    public static void main(String[] args) {
        //java 7 的Diamond语法
        Collection<String> list = new ArrayList<>();
        list.add("ruby");
        list.add("weiss");

        //编译不能通过
//        list.add(1);

        //但是可以用过泛型擦写，骗过编译器
        Collection collection = list;
        collection.add(1);

        System.out.println(list);
    }
}
