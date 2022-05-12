package top.thegodisgad.user.service;

import top.thegodisgad.user.entity.UserLoginLog;

/**
* @author yhdhz
* @description 针对表【tb_user_login_log(用户登录日志)】的数据库操作Service
* @createDate 2022-05-19 16:17:13
*/
public interface UserLoginLogService  {
    //登录
    /*--------------------------------------------------------*/
    
    public boolean login(UserLoginLog userLoginLog);
    /*--------------------------------------------------------*/
}
