package top.thegodisgad.utils.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserName,String> {
    @Override
    public void initialize(UserName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(Pattern.matches("[a-zA-z\\d]*",value)){
            return true;
        }
        return false;
    }
}
