package top.thegodisgad.tag.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.tag.entity.UserTag;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user_tag(拥有的标签(所有标签))】的数据库操作Mapper
 * @createDate 2022-05-19 16:16:44
 * @Entity top.thegodisgad.tag.entity.UserTag
 */
@Repository
@Mapper
public interface UserTagMapper {
    /**
     * insert
     *
     * @return int
     * @author 杜洪洲
     * @description: insert
     * @date 2022/5/27 14:20
     */
    public int insert(UserTag userTag);

    /**
     * selectByUserId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.UserTag>
     * @author 杜洪洲
     * @description: selectByUserId
     * @date 2022/5/27 14:20
     */
    public List<UserTag> selectByUserId(@Param(value = "userId") Long userId);

    /**
     * selectByTagId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.UserTag>
     * @author 杜洪洲
     * @description: selectByTagId
     * @date 2022/5/27 14:20
     */
    public List<UserTag> selectByTagId(@Param(value = "tagId") Long tagId);

    /**
     * delete
     *
     * @return int
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 14:21
     */
    public int delete(UserTag userTag);
}




