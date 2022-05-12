package top.thegodisgad.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.user.entity.UserLoginLog;
import top.thegodisgad.user.mapper.UserLoginLogMapper;
import top.thegodisgad.user.service.UserLoginLogService;

/**
* @author yhdhz
* @description 针对表【tb_user_login_log(用户登录日志)】的数据库操作Service实现
* @createDate 2022-05-19 16:17:13
*/
@Service
public class UserLoginLogServiceImpl 
    implements UserLoginLogService{
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    public UserLoginLogServiceImpl(UserLoginLogMapper userLoginLogMapper) {
        this.userLoginLogMapper = userLoginLogMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public boolean login(UserLoginLog userLoginLog) {
        if(userLoginLog.getLoginStatus()==1)
        {
            userLoginLog.setCertificate(null);
        }
        return userLoginLogMapper.insert(userLoginLog)==1;
    }
}




