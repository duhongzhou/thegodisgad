package top.thegodisgad.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.user.entity.UserRelation;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_user_relation(关系)】的数据库操作Mapper
* @createDate 2022-05-19 16:17:17
* @Entity top.thegodisgad.user.entity.UserRelation
*/
@Repository
@Mapper
public interface UserRelationMapper  {
    //
    /*--------------------------------------------------------*/

    public int insert(UserRelation userRelation);
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    public int update(UserRelation userRelation);
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    public List<UserRelation> selectByUserId(@Param(value = "userId") Long userId);
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    /**
     * selectByUserIdAndFriendId
     *
     * @author 杜洪洲
     * @description: TODO:selectByUserIdAndFriendId
     * @date 2022/5/23 17:41
     * @return UserRelation
     * @throw
     */
    public UserRelation selectByUserIdAndFriendId(@Param(value = "userId") Long userId,@Param(value = "friendId") Long friendId);
    /*--------------------------------------------------------*/
}




