package top.thegodisgad.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.blog.entity.BlogPost;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_blog_post(博客文章(贴子))】的数据库操作Mapper
* @createDate 2022-05-19 16:17:47
* @Entity top.thegodisgad.blog.entity.BlogPost
*/
@Repository
@Mapper
public interface BlogPostMapper  {
    int insert(BlogPost blogPost);

   int deleteByBlogIdAndUserId(@Param(value = "blogId")Long blogId,@Param(value = "uerId")Long userId);

   List<BlogPost> searchAllByBlogId(@Param(value = "blogId")Long blogId);
   List<BlogPost> searchAllByUserId(@Param(value = "userId")Long userId);
   List<BlogPost> searchAllByBlogIds(List<Long> blogIds);
   List<BlogPost> searchAll(@Param(value = "start") Integer start,@Param(value = "length")Integer length);

   int updateBadReviewByBlogIdInt(@Param(value = "blogId")Long blogId,@Param(value = "badReview") Integer badReview);
   int updateGoodReviewByBlogIdInt(@Param(value = "blogId")Long blogId,@Param(value = "goodReview") Integer goodReview);
   int updateBrowseNumberByBlogIdInt(@Param(value = "blogId")Long blogId,@Param(value = "goodReview") Integer browseNumber);
   int updateReviewerNumberByBlogIdInt(@Param(value = "blogId")Long blogId,@Param(value = "reviewNumber") Integer reviewNumber);
}




