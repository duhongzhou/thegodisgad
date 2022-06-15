package top.thegodisgad.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.blog.entity.BlogPost;
import top.thegodisgad.blog.mapper.BlogPostMapper;
import top.thegodisgad.blog.service.BlogPostService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.service.UserService;
import top.thegodisgad.utils.IDUtils;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_blog_post(博客文章(贴子))】的数据库操作Service实现
 * @createDate 2022-05-19 16:17:47
 */
@Service
public class BlogPostServiceImpl
        implements BlogPostService {
    BlogPostMapper blogPostMapper;
    UserService userService;

    @Autowired
    public BlogPostServiceImpl(BlogPostMapper blogPostMapper, UserService userService) {
        this.blogPostMapper = blogPostMapper;
        this.userService = userService;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(BlogPost blogPost) {
        Long id = IDUtils.createId();
        blogPost.setBlogId(id);
        int insert = blogPostMapper.insert(blogPost);
        return insert == 1 ? id : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(BlogPost blogPost) {
        return blogPostMapper.deleteByBlogIdAndUserId(blogPost.getBlogId(), blogPost.getUserId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BlogPost> searchAllByBlogId(Long blogId) {
        List<BlogPost> blogPosts = blogPostMapper.searchAllByBlogId(blogId);
        blogPosts.forEach(blogPost -> {
            User user = userService.selectUserId(blogPost.getUserId());
            blogPost.setUser(user);
        });
        return blogPosts;
    }

    @Override
    public List<BlogPost> searchAllByBlogIds(List<Long> blogIds) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BlogPost> searchAll(Integer start, Integer length) {
        List<BlogPost> blogPosts = blogPostMapper.searchAll(start, length);
        blogPosts.forEach(blogPost -> {
            User user = userService.selectUserId(blogPost.getUserId());
            blogPost.setUser(user);
        });
        return blogPosts;
    }

    /**
     *
     */
    @Override
    public List<BlogPost> searchAllByUserId(Long userId) {
        return blogPostMapper.searchAllByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBadReviewByBlogIdInt(Long blogId, Integer badReview) {
        return blogPostMapper.updateBadReviewByBlogIdInt(blogId, badReview);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGoodReviewByBlogIdInt(Long blogId, Integer goodReview) {
        return blogPostMapper.updateGoodReviewByBlogIdInt(blogId, goodReview);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBrowseNumberByBlogIdInt(Long blogId, Integer browseNumber) {
        return blogPostMapper.updateBrowseNumberByBlogIdInt(blogId, browseNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateReviewerNumberByBlogIdInt(Long blogId, Integer reviewNumber) {
        return blogPostMapper.updateReviewerNumberByBlogIdInt(blogId, reviewNumber);
    }
}




