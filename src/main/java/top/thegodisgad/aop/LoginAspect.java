package top.thegodisgad.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;
import top.thegodisgad.utils.token.RedisOperator;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author yhdhz
 */
@Aspect
@Component
public class LoginAspect {
    RedisOperator redisOperator;

    @Autowired
    public LoginAspect(RedisOperator redisOperator) {
        this.redisOperator = redisOperator;
    }

    @Pointcut(value = "execution(* top.thegodisgad.*.controller.*.*(..)) || execution(* top.thegodisgad.*.controller.*.*.*(..))")

    public void domain() {

    }

    @Before("domain()")
    public void before(JoinPoint joinPoint) throws UserException {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest();
        
        User user = redisOperator.getUser(request);
        if (user == null) {
            throw new UserException(UserExceptionEnum.PLEASE_LOGIN);
        }
    }
}
