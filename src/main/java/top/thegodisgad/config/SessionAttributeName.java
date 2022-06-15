package top.thegodisgad.config;

import top.thegodisgad.user.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhdhz
 */
public class SessionAttributeName {

    public static final String USER_AUTHORIZED = "dhz";
    public static final String USER = "dhzUser";
    public static Long getUserId(HttpServletRequest request){
        User user =(User) request.getSession().getAttribute(USER);
        return user.getUserId();
    }
}
