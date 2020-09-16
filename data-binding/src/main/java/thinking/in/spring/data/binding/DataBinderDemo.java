package thinking.in.spring.data.binding;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.HashMap;
import java.util.Map;

public class DataBinderDemo {


    public static void main(String[] args) {
        //创建空白对象
        User user = new User();

        //1.创建DataBinder对象
        DataBinder binder = new DataBinder(user,"user");

        //2.创建PropertyValues
        Map<String,Object> source = new HashMap<>();
        source.put("id",1L);
        source.put("name","ruby");

        //PropertyValues存在user中不存在的属性
        //DataBinder会忽略不存在的属性
        source.put("age",18);

        //PropertyValues 存在一个嵌套属性，比如Company.name
        //DataBinder支持嵌套属性
        //DataBinder手首先创建一个Company对象
        //company.setName(name);
        //user.setCompany(company);
        source.put("company.name","jzl");
        PropertyValues propertyValues = new MutablePropertyValues(source);

        //细粒度操控
        //1 调整ignoreUnknownFields-》是否忽略未知字段-》默认值是true
        //结果：如果是否false，存在未知的参数时，会抛出异常 org.springframework.beans.NotWritablePropertyException
//        binder.setIgnoreUnknownFields(false);

        //2.ignoreInvalidFields 是否忽略非法字段 -》 默认值是false
        //这个字段需要配合autoGrowNestedPaths 是否自动添加嵌套路径 -》默认值是true
        //如果autoGrowNestedPaths是true，不管ignoreInvalidFields是true还是false都不会报错
        //如果autoGrowNestedPaths是false，ignoreInvalidFields是true也不会报错
        //如果autoGrowNestedPaths是false，ignoreInvalidFields是false则会抛出异常
        binder.setAutoGrowNestedPaths(false);
        binder.setIgnoreInvalidFields(true);

        //requiredFields 设置必须绑定的字段
        //这个如果PropertyValues中不存在某个字段的值，不会直接抛出异常，错误信息通过BindingResult返回
        binder.setRequiredFields("id","name","city");

        binder.bind(propertyValues);

        System.out.println(user);

        //获取绑定结果
        BindingResult bindingResult = binder.getBindingResult();
        System.out.println(bindingResult);

    }
}
