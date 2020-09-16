package thinking.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.*;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.List;
import java.util.Locale;

public class UserValidatorDemo {
    public static void main(String[] args) {
        User user = new User();
//        user.setId(1L);
//        user.setName("ruby");
        UserValidator validator = new UserValidator();
        System.out.printf("User 对象是否被 UserValidator 支持校验：%s\n",validator.supports(User.class));
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);
        List<ObjectError> allErrors = errors.getAllErrors();
        MessageSource messageSource = createMessage();
        for (ObjectError error : allErrors) {
            String message = messageSource.getMessage(error.getCode(),error.getArguments(),Locale.getDefault());
            System.out.println(message);
        }
    }

    private static MessageSource createMessage() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 的属性不能为空！");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not  be null");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not  be null");
        return messageSource;
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"id","id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","name.required");
            String userName = user.getName();
            // ...
        }
    }
}
