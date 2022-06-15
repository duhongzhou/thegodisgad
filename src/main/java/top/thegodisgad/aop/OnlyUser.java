package top.thegodisgad.aop;

import java.lang.annotation.*;

/**
 * @author yhdhz
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OnlyUser {
    String value() default "";

}
