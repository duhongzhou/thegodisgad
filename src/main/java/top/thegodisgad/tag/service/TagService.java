package top.thegodisgad.tag.service;

import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.user.entity.User;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_tag(标签)】的数据库操作Service
 * @createDate 2022-05-19 16:16:20
 */
public interface TagService {
    /**
     * add
     *
     * @return boolean
     * @author 杜洪洲
     * @description: add
     * @date 2022/5/27 12:31
     */
    public Long add(Tag tag);

    /**
     * delete
     *
     * @return boolean
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 12:32
     */
    public boolean delete(Tag tag);

    /**
     * delete
     *
     * @return boolean
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 12:32
     */
    public boolean delete(Long tagId);

    /**
     * findUserCreateTag
     *
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     * @author 杜洪洲
     * @description: findUserCreateTag
     * @date 2022/5/27 12:32
     */
    public List<Tag> findUserCreateTag(User user);


    /**
     * findTagBayTagId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     * @author 杜洪洲
     * @description: findTagBayTagId
     * @date 2022/5/27 14:47
     */
    public List<Tag> findTagByTagIds(List<Long> tageId);

    /**
     * findUserCreateTag
     *
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     * @author 杜洪洲
     * @description: findUserCreateTag
     * @date 2022/5/27 12:32
     */
    public List<Tag> findUserCreateTag(Long userId);

    /**
     * findTagBayTagId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     * @author 杜洪洲
     * @description: findTagBayTagId
     * @date 2022/5/27 12:32
     */
    public List<Tag> findTagByTagId(Long tageId);

    /**
     * findTagBayTagName
     *
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     * @author 杜洪洲
     * @description: findTagBayTagName
     * @date 2022/5/27 12:32
     */
    public List<Tag> findTagByTagName(String tageName);

    /**
     * findTagByTagNameAndTagProperty
     *
     * @return java.lang.Long
     * @author 杜洪洲
     * @description: findTagByTagNameAndTagProperty
     * @date 2022/5/27 15:01
     */
    public Long findTagIdByTagNameAndTagProperty(Tag tag);

    /**
     * findTagIdAndNullCreate
     *
     * @return java.lang.Long
     * @author 杜洪洲
     * @description: findTagIdAndNullCreate
     * @date 2022/5/27 15:08
     */
    public Long findTagIdAndNullCreate(Tag tag);


}
