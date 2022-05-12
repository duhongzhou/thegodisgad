package top.thegodisgad.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.mapper.UserMapper;
import top.thegodisgad.user.service.UserAuthorizedService;
import top.thegodisgad.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user(用户)】的数据库操作Service实现
 * @createDate 2022-05-19 16:21:13
 */
@Service
public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    UserAuthorizedService userAuthorizedService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserAuthorizedService userAuthorizedService) {
        this.userMapper = userMapper;
        this.userAuthorizedService = userAuthorizedService;
    }




    /**
     * @param userAuthorized
     * @return boolean
     * @description: TODO:登录
     * @author 杜洪洲
     * @date 2022/5/19 20:16
     * @throw UserException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long login(UserAuthorized userAuthorized, HttpServletRequest request) throws UserException {
        Long login = userAuthorizedService.login(userAuthorized,request);

        return login;
    }


    /**
     * @param user
     * @return boolean
     * @description: 修改信息
     * @author 杜洪洲
     * @date 2022/5/19 20:16
     * @throw UserException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editInformation(User user) throws UserException {
        int i = userMapper.updateForEditInformation(user);

        return 1 == i;
    }


    /**
     * @param user
     * @return boolean
     * @description: 绑定手机
     * @author 杜洪洲
     * @date 2022/5/19 20:16
     * @throw UserException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindMobile(User user) throws UserException {

        int i = userMapper.updateForBindPhone(user);
        return 1 == i;
    }


    /**
     * @param user
     * @return boolean
     * @description: 绑定邮箱
     * @author 杜洪洲
     * @date 2022/5/19 20:16
     * @throw UserException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean bindEmail(User user) throws UserException {
        int i = userMapper.updateForBindEmail(user);
        return i == 1;
    }


    /**
     * @param user
     * @return java.util.List<top.thegodisgad.user.entity.User>
     * @description: 查找用户
     * @author 杜洪洲
     * @date 2022/5/19 20:18
     * @throw UserException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> find(User user) throws UserException {
        return userMapper.selectUsers(user);
    }

    /**
     * @param user
     * @return boolean
     * @description: TODO:this is to do
     * @author 杜洪洲
     * @date 2022/5/19 20:26
     * @throw
     */
    @Override
    public boolean logout(User user) {
        int i = userMapper.updateForLogout(user);
        return 1 == i;
    }
    /**
     * itExists
     *
     * @author 杜洪洲
     * @description: itExists
     * @date 2022/5/23 17:13
     * @param userId
     * @return boolean
     * @throw
     */
    @Override
    public boolean itExists(Long userId) {

        return userMapper.selectByUserId(userId) == null ? false : true;
    }

    @Override
    public User getInformation(Long userId) {
        User user = userMapper.selectByUserId(userId);
        return user;
    }

    @Override
    public boolean updateInformation(User user) {
        int i = userMapper.updateForEditInformation(user);

        return 1 == i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long registered(User user) throws UserException{
        user.registered();
        int i = userMapper.insertUser(user);
        return user.getUserId();
    }


}




