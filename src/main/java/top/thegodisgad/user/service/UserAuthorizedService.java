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
     * @return boolean
     * @description: login
     * @author 杜洪洲
     * @date 2022/5/22 15:28
     * @throw
     */
    public Long login(UserAuthorized userAuthorized, HttpServletRequest request);

    /*--------------------------------------------------------*/
    public boolean verifyOldCertificate(String identifier, String oldCertificate);
    /*--------------------------------------------------------*/

    /**
     * @return boolean
     * @description: registered
     * @author 杜洪洲
     * @date 2022/5/22 15:29
     * @throw
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean registered(UserAuthorized userAuthorized);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * changeCertificate
     *
     * @return boolean
     * @author 杜洪洲
     * @description: changeCertificate
     * @date 2022/5/24 18:45
     * @throw
     */

    public boolean changeCertificate(UserAuthorized userAuthorized) throws SQLIntegrityConstraintViolationException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * @return boolean
     * @description: logout
     * @author 杜洪洲
     * @date 2022/5/22 15:32
     * @throw
     */
    public boolean logout(UserAuthorized userAuthorized);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * getUserId
     *
     * @return java.lang.Long
     * @author 杜洪洲
     * @description: getUserId
     * @date 2022/5/24 16:05
     * @throw
     */
    public Long getUserId(String identifier);

    UserAuthorized findCertificateByUserId(Long userId);

    boolean registeredOut(Long userId);
    /*--------------------------------------------------------*/
}
