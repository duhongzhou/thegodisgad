package top.thegodisgad.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.user.entity.User;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user(用户)】的数据库操作Mapper
 * @createDate 2022-05-19 16:21:13
 * @Entity top.thegodisgad.user.entity.User
 */
@Repository
@Mapper
public interface UserMapper {
    /*--------------------------------------------------------*/

    /**
     *
     */
    public User selectByUserId(@Param("userId") Long userId);

    /*--------------------------------------------------------*/
    public List<User> selectByUserIds(List<Long> userIds);
    /*--------------------------------------------------------*/

    /**
     *
     */
    public List<User> selectUsers(User user);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * 插入用户
     */
    public int insertUser(User user);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * 注销
     */
    public int updateForLogout(User user);
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * 修改信息
     */
    public int updateForEditInformation(User user);
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * 绑定手机
     */
    public int updateForBindPhone(User user);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * 绑定邮箱
     */
    public int updateForBindEmail(User user);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    public int updateInformation(User user);

    User selectByUserName(@Param(value = "userName") String userName);

    int deleteByUserId(@Param(value = "userId") Long userId);

    /*--------------------------------------------------------*/
}




