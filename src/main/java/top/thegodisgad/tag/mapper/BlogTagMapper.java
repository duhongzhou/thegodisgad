package top.thegodisgad.tag.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.tag.entity.BlogTag;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_blog_tag(拥有的标签(所有标签))】的数据库操作Mapper
 * @createDate 2022-05-19 16:16:02
 * @Entity top.thegodisgad.tag.entity.BlogTag
 */
@Repository
@Mapper
public interface BlogTagMapper {
    /**
     * insert
     *
     * @return int
     * @author 杜洪洲
     * @description: insert
     * @date 2022/5/27 14:36
     */
    public int insert(BlogTag blogTag);

    /**
     * selectByBlogId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.BlogTag>
     * @author 杜洪洲
     * @description: selectByBlogId
     * @date 2022/5/27 14:36
     */
    public List<BlogTag> selectByBlogId(@Param(value = "blogId") Long blogId);

    /**
     * selectByTagId
     *
     * @return java.util.List<top.thegodisgad.tag.entity.BlogTag>
     * @author 杜洪洲
     * @description: selectByTagId
     * @date 2022/5/27 14:36
     */
    public List<BlogTag> selectByTagId(@Param(value = "tagId") Long tagId);

    /**
     * delete
     *
     * @return int
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 14:36
     */
    public int delete(BlogTag blogTag);
}




