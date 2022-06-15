package top.thegodisgad.tag.service;

import top.thegodisgad.blog.entity.BlogPost;
import top.thegodisgad.tag.entity.BlogTag;
import top.thegodisgad.tag.entity.Tag;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_blog_tag(拥有的标签(所有标签))】的数据库操作Service
 * @createDate 2022-05-19 16:16:02
 */
public interface BlogTagService {
    /**
     * add
     *
     * @return int
     * @author 杜洪洲
     * @description: add
     * @date 2022/5/27 14:46
     */
    public int add(BlogTag blogTag,Tag tag);

    /**
     * delete
     *
     * @return int
     * @author 杜洪洲
     * @description: delete
     * @date 2022/5/27 14:47
     */
    public int delete(BlogTag blogTag);
    /**
     * findByUserId
     *
     * @author 杜洪洲
     * @description: findByUserId
     * @date 2022/5/27 14:54
     * @return java.util.List<top.thegodisgad.tag.entity.Tag>
     */
    public List<Tag> findByBlogId(Long blogId);
    /**
     * findByTagId
     *
     * @author 杜洪洲
     * @description: findByTagId
     * @date 2022/5/27 14:54
     * @return java.util.List<top.thegodisgad.blog.entity.BlogPost>
     */
    public List<BlogPost> findByTagId(Long tagId);
}
