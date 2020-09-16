package thinking.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import thinking.in.spring.ioc.container.overview.domain.User;

import java.util.List;
import java.util.Locale;

public class ErrorsMessageDemo {

    public static void main(String[] args) {
        User user = new User();
        user.setName("ruby");
        Errors errors = new BeanPropertyBindingResult(user, "user");
        errors.reject("user.properties.not.null");
        errors.rejectValue("name","name.required");

        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        MessageSource messageSource = createMessage();

        for (ObjectError error : allErrors) {
            String message = messageSource.getMessage(error.getCode(),
                    error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    public static MessageSource createMessage() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 的属性不能为空！");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not  be null");
        return messageSource;
    }
}
