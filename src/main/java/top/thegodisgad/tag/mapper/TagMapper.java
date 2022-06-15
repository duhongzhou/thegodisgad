package top.thegodisgad.tag.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.tag.entity.Tag;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_tag(标签)】的数据库操作Mapper
* @createDate 2022-05-19 16:16:20
* @Entity top.thegodisgad.tag.entity.Tag
*/
@Mapper
@Repository
public interface TagMapper  {
    /**
     * insert
     *
     * @author 杜洪洲
     * @description: insert
     * @date 2022/5/27 11:58
     * @return int
     */
    public int insert(Tag tag) throws SQLIntegrityConstraintViolationException;
    /**
     * selectByTagId
     *
     * @author 杜洪洲
     * @description: selectByTagId
     * @date 2022/5/27 11:59
     * @return int
     */
    public List<Tag> selectByTagId(ArrayList<Long> tagIds);

    /**
     * selectByTagName
     *
     * @author 杜洪洲
     * @description: selectByTagName
     * @date 2022/5/27 12:20
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     */
    public List<Tag> selectByTagName(@Param(value = "tagName") String tagName);
    /**
     * selectByCreatorId
     *
     * @author 杜洪洲
     * @description: selectByCreatorId
     * @date 2022/5/27 15:02
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     */
    public List<Tag> selectByCreatorId(@Param(value = "creator") Long creator);
    /**
     * selectTagByTagNameAndTagProperty
     *
     * @author 杜洪洲
     * @description: selectTagByTagNameAndTagProperty
     * @date 2022/5/27 15:03
     * @return java.lang.Long
     */
    public Long selectTagByTagNameAndTagProperty(Tag tag);

    /**
     * delete
     *
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 11:59
     * @return int
     */
    public int delete(@Param(value = "tagId") Long tagId);
}




