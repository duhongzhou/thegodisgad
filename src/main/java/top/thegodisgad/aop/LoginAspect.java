package top.thegodisgad.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.thegodisgad.config.SessionAttributeName;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.exception.UserException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhdhz
 */
@Aspect
@Component
public class LoginAspect {
    @Pointcut(value = "execution(* top.thegodisgad.*.controller.*.*(..))")
    public void domain(){

    }
    @Before("domain()")
    public void before(JoinPoint joinPoint)throws UserException{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        User user = (User) request.getSession().getAttribute(SessionAttributeName.USER);
//        if(user==null)
//        {
//            throw new UserException(UserExceptionEnum.PLEASE_LOGIN);
//        }
    }
}
