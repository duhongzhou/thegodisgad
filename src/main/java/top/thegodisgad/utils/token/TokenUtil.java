package top.thegodisgad.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.utils.RequestUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Component
public class TokenUtil {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public TokenUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 生成token
     *
     * @param userAuthorized
     * @return
     */
    public static String generateToken(UserAuthorized userAuthorized) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create()
                .withClaim("userId", userAuthorized.getUserId())
                .withClaim("identifier", userAuthorized.getIdentifier())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256("dhzdhz"));
        return token;
    }

    public static String temporaryToken(HttpServletRequest request) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 5 * 60 * 1000;
        //5分钟有效时间
        Date end = new Date(currentTime);
        String ip = RequestUtil.getIp(request);
        String
                token = JWT.create()
                .withAudience(ip)
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(ip));
        return token;
    }


    /**
     * @param token
     * @param key
     * @return userId
     * 获取制定token中某个属性值
     */
    public static String get(String token, String key) {
        Map<String, Claim> claims = JWT.decode(token).getClaims();
        String str = claims.get(key).asString();
        return str;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }


    /**
     * @param request
     * @return 获取token
     */
    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c :
                cookies) {
            if (c.getName().equals("token")) {
                return c.getValue();
            }
        }
        return null;
    }
}