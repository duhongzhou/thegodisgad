package top.thegodisgad.utils.token;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author yhdhz
 */
@Component
public class RedisOperator {
    private StringRedisTemplate redisTemplate;

    @Autowired
    public RedisOperator(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public Boolean remove(String token) {
        Boolean delete = redisTemplate.delete(token);

        return delete;
    }

    public String setUser(User user) {
        return JSON.toJSONString(user);
    }

    public User getUser(HttpServletRequest request) throws UserException {
        String token = request.getParameter("token");
        if (token == null) {
            throw new UserException(UserExceptionEnum.PLEASE_LOGIN);
        }
        String s = get(token);

        User user = JSON.parseObject(s, User.class);

        return user;

    }

}
