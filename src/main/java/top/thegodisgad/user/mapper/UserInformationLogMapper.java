package top.thegodisgad.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.user.entity.UserInformationLog;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_user_information_log(表用户信息修改日志注释)】的数据库操作Mapper
* @createDate 2022-05-19 16:17:09
* @Entity top.thegodisgad.user.entity.UserInformationLog
*/
@Repository
@Mapper
public interface UserInformationLogMapper  {
    /*--------------------------------------------------------*/
    /**
     * insert 本表不允许修改
     *
     * @author 杜洪洲
     * @description: TODO:insert
     * @date 2022/5/24 17:34
     * @param userInformationLog
     * @return int
     * @throw
     */
    public int insert(UserInformationLog userInformationLog);
    /*--------------------------------------------------------*/
    /**
     * selectByUserId
     *
     * @author 杜洪洲
     * @description: TODO:selectByUserId
     * @date 2022/5/24 17:36
     * @param userId
     * @return top.thegodisgad.user.entity.UserInformationLog
     * @throw
     */
    public List<String> selectByUserIdForCertificate(@Param(value = "userId") Long userId);
}




