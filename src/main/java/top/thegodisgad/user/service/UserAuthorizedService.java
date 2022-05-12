package top.thegodisgad.user.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.user.entity.UserAuthorized;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author yhdhz
 * @description 针对表【tb_user_authorized(用户授权)】的数据库操作Service
 * @createDate 2022-05-19 16:17:06
 */
public interface UserAuthorizedService {
    // 注册
    // 登录
    // 更改密码
    // 注销

    /*--------------------------------------------------------*/

    /**
     * @param userAuthorized
     * @return boolean
     * @description: login
     * @author 杜洪洲
     * @date 2022/5/22 15:28
     * @throw
     */
    public Long login(UserAuthorized userAuthorized, HttpServletRequest request);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * @param userAuthorized
     * @return boolean
     * @description: registered
     * @author 杜洪洲
     * @date 2022/5/22 15:29
     * @throw
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public boolean registered(UserAuthorized userAuthorized) ;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * changeCertificate
     *
     * @author 杜洪洲
     * @description: TODO:changeCertificate
     * @date 2022/5/24 18:45
     * @param userAuthorized
     * @return boolean
     * @throw
     */

    public boolean changeCertificate(UserAuthorized userAuthorized) throws SQLIntegrityConstraintViolationException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /**
     *
     * @description: logout
     * @author 杜洪洲
     * @date 2022/5/22 15:32
     * @param userAuthorized
     * @return boolean
     * @throw
     */
    public boolean logout(UserAuthorized userAuthorized);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /**
     * getUserId
     *
     * @author 杜洪洲
     * @description: TODO:getUserId
     * @date 2022/5/24 16:05
     * @param identifier
     * @return java.lang.Long
     * @throw
     */
    public Long getUserId(String identifier);
    /*--------------------------------------------------------*/
}
