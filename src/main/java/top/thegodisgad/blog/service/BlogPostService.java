package top.thegodisgad.blog.service;

import top.thegodisgad.blog.entity.BlogPost;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_blog_post(博客文章(贴子))】的数据库操作Service
* @createDate 2022-05-19 16:17:47
*/
public interface BlogPostService  {

    Long add(BlogPost blogPost);
    int delete(BlogPost blogPost);
    List<BlogPost> searchAllByBlogId(Long blogId);
    List<BlogPost> searchAllByBlogIds(List<Long> blogIds);
    List<BlogPost> searchAll(Integer start,Integer length);
    List<BlogPost> searchAllByUserId(Long userId);


    int updateBadReviewByBlogIdInt(Long blogId, Integer badReview);
    int updateGoodReviewByBlogIdInt(Long blogId, Integer goodReview);
    int updateBrowseNumberByBlogIdInt(Long blogId, Integer browseNumber);
    int updateReviewerNumberByBlogIdInt(Long blogId, Integer reviewNumber);


}
