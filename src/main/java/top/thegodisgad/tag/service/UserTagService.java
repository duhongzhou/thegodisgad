package top.thegodisgad.tag.service;

import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.tag.entity.UserTag;
import top.thegodisgad.user.entity.User;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user_tag(拥有的标签(所有标签))】的数据库操作Service
 * @createDate 2022-05-19 16:16:44
 */
public interface UserTagService {

    /**
     * add
     *
     * @return boolean
     * @author 杜洪洲
     * @description: add
     * @date 2022/5/27 14:53
     */
    public boolean add(UserTag userTag,Tag tag);

    /**
     * delete
     *
     * @return boolean
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 14:53
     */
    public boolean delete(UserTag userTag);

    /**
     * findByUserId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     * @author 杜洪洲
     * @description: findByUserId
     * @date 2022/5/27 14:55
     */
    public List<Tag> findByUserId(Long userId);

    /**
     * findByTagId
     *
     * @return java.util.List<top.thegodisgad.user.entity.User>
     * @author 杜洪洲
     * @description: findByTagId
     * @date 2022/5/27 14:55
     */
    public List<User> findByTagId(Long tagId);
}
