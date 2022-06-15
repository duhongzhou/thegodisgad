package top.thegodisgad.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.user.entity.UserAuthorized;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author yhdhz
 * @description 针对表【tb_user_authorized(用户授权)】的数据库操作Mapper
 * @createDate 2022-05-19 16:17:06
 * @Entity top.thegodisgad.user.entity.UserAuthorized
 */
@Repository
@Mapper
public interface UserAuthorizedMapper {
    // 注册
    // 登录
    // 更改密码
    // 注销

    /**
     * insert
     *
     * @return int
     * @author 杜洪洲
     * @description: TODO:insert
     * @date 2022/5/22 16:00
     * @throw SQLIntegrityConstraintViolationException
     */
    int insert(UserAuthorized userAuthorized);

    /**
     * selectAllByIdentifier
     *
     * @return top.thegodisgad.user.entity.UserAuthorized
     * @author 杜洪洲
     * @description: TODO:selectAllByIdentifier
     * @date 2022/5/23 15:56
     * @throw
     */
    UserAuthorized selectAllByIdentifier(@Param("identifier") String identifier);

    /**
     * updateCertificateByUserId
     *
     * @return int
     * @author 杜洪洲
     * @description: TODO:updateCertificateByUserId
     * @date 2022/5/22 15:59
     * @throw SQLIntegrityConstraintViolationException
     */
    int updateCertificateByUserId(@Param("userId") Long userId, @Param("certificate") String certificate) throws SQLIntegrityConstraintViolationException;

    /**
     * updateIsValidByUserId
     *
     * @return int
     * @description: updateIsValidByUserId
     * @author 杜洪洲
     * @date 2022/5/22 15:56
     */
    int updateIsValidByUserId(@Param("userId") Long userId, @Param("isValid") Integer isValid);

    /**
     * selectUserIdByIdentifier
     *
     * @return java.lang.Long
     * @author 杜洪洲
     * @description: TODO:selectUserIdByIdentifier
     * @date 2022/5/24 16:08
     * @throw
     */
    Long selectUserIdByIdentifier(@Param(value = "identifier") String identifier);

    UserAuthorized selectCertificateByUserId(@Param(value = "userId") Long userId);

    int deletByUserId(@Param(value = "userId") Long userId);
}




