package top.thegodisgad.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.thegodisgad.user.entity.UserLoginLog;

/**
* @author yhdhz
* @description 针对表【tb_user_login_log(用户登录日志)】的数据库操作Mapper
* @createDate 2022-05-19 16:17:13
* @Entity top.thegodisgad.user.entity.UserLoginLog
*/
@Repository
@Mapper
public interface UserLoginLogMapper  {
    //登录
    /*--------------------------------------------------------*/
    /**
     * insert
     *
     * @author 杜洪洲
     * @description: TODO:insert
     * @date 2022/5/23 13:07
     * @return int
     * @throw
     */
    public int insert(UserLoginLog userLoginLog);
    /*--------------------------------------------------------*/
}




