package top.thegodisgad.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author yhdhz
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.SOURCE)
public @interface Method {
    public String author() default "杜洪洲";
    public String name() default "method";
    public String returnType() default "void";
    public String[] parameter() default {"null"};
    public String value() default "";
}
