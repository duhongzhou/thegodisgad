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
public interface UserService  {
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
     * @author 杜洪洲
     * @description: TODO:registered
     * @date 2022/5/24 19:34
     * @param user
     * @return top.thegodisgad.vo.response.Response
     * @throw
     */
    public Long registered(User user);


    /*--------------------------------------------------------*/

    /**
     * 注册
     * @param userAuthorized
     * @param request
     * @return
     * @throws UserException
     */

    Long login(UserAuthorized userAuthorized, HttpServletRequest request) throws UserException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /**
     * asd
     * @param user
     * @return
     * @throws UserException
     */
     boolean editInformation(User user) throws UserException;
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/
    /**
     * bindMobile
     *
     * @author 杜洪洲
     * @description: TODO:bindMobile
     * @date 2022/5/24 19:35
     * @param user
     * @return boolean
     * @throws UserException
     */
     boolean bindMobile(User user) throws UserException ;

    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/


    /**
     * bindEmail
     *
     * @author 杜洪洲
     * @description: TODO:bindEmail
     * @date 2022/5/24 19:36
     * @param user
     * @return boolean
     * @throws UserException
     */
     boolean bindEmail(User user) throws UserException ;
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * find
     *
     * @param user
     * @return
     * @throws UserException
     */
     List<User> find(User user) throws UserException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * 注销
     * @param user
     * @return
     */
    public boolean logout(User user);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /**
     * itExists
     *
     * @author 杜洪洲
     * @description: itExists
     * @date 2022/5/23 17:11
     * @param userId
     * @return boolean
     * @throw
     */
    public boolean itExists(Long userId);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /**
     * getInfomation
     *
     * @author 杜洪洲
     * @description: TODO:getInfomation
     * @date 2022/5/24 15:54
     * @param userId
     * @return top.thegodisgad.user.entity.User
     * @throw
     */
    public User getInformation(Long userId);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /**
     * updateInformation
     *
     * @author 杜洪洲
     * @description: TODO:updateInformation
     * @date 2022/5/24 17:13
     * @param user
     * @return boolean
     * @throw
     */
    public boolean updateInformation(User user);
    /*--------------------------------------------------------*/
}
