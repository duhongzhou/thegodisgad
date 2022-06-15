package top.thegodisgad.user.service;

import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.exception.UserException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user(用户)】的数据库操作Service
 * @createDate 2022-05-19 16:21:13
 */
public interface UserService {
    // 注册
    // 账号注册
    // 手机注册
    // 邮箱注册
    // 其他网站注册
    // 批量注册

    // 登录
    // 修改信息
    // 绑定手机
    // 绑定邮箱
    //

    /*--------------------------------------------------------*/

    /**
     * registered
     *
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: registered
     * @date 2022/5/24 19:34
     * @throw
     */
    public Long registered(User user);


    /*--------------------------------------------------------*/

    /**
     * 注册
     */

    Long login(UserAuthorized userAuthorized, HttpServletRequest request) throws UserException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * asd
     */
    boolean editInformation(User user) throws UserException;
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * bindMobile
     *
     * @return boolean
     * @author 杜洪洲
     * @description: bindMobile
     * @date 2022/5/24 19:35
     */
    boolean bindMobile(User user) throws UserException;

    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/


    /**
     * bindEmail
     *
     * @return boolean
     * @author 杜洪洲
     * @description: bindEmail
     * @date 2022/5/24 19:36
     */
    boolean bindEmail(User user) throws UserException;
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * find
     */
    List<User> find(User user) throws UserException;
    /*--------------------------------------------------------*/

    /**
     * findByUserIds
     *
     * @return java.util.List<top.thegodisgad.user.entity.User>
     * @author 杜洪洲
     * @description: findByUserIds
     * @date 2022/5/28 13:58
     */
    List<User> findByUserIds(List<Long> userIds);
    /*--------------------------------------------------------*/

    /**
     * 注销
     */
    public boolean logout(User user);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * itExists
     *
     * @return boolean
     * @author 杜洪洲
     * @description: itExists
     * @date 2022/5/23 17:11
     * @throw
     */
    public boolean itExists(Long userId);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * getInfomation
     *
     * @return top.thegodisgad.user.entity.User
     * @author 杜洪洲
     * @description: getInfomation
     * @date 2022/5/24 15:54
     * @throw
     */
    public User getInformation(Long userId);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * updateInformation
     *
     * @return boolean
     * @author 杜洪洲
     * @description: updateInformation
     * @date 2022/5/24 17:13
     * @throw
     */
    public boolean updateInformation(User user);

    User selectUserId(Long userId);

    boolean registeredOut(Long userId);
    /*--------------------------------------------------------*/
}
