package top.thegodisgad.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.blog.entity.BlogOperation;
import top.thegodisgad.blog.entity.BlogPost;
import top.thegodisgad.blog.mapper.BlogOperationMapper;
import top.thegodisgad.blog.service.BlogOperationService;
import top.thegodisgad.blog.service.BlogPostService;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_blog_operation(博客文章操作)】的数据库操作Service实现
 * @createDate 2022-05-19 16:17:38
 */
@Service
public class BlogOperationServiceImpl
        implements BlogOperationService {
    BlogOperationMapper blogOperationMapper;
    BlogPostService blogPostService;

    @Autowired
    public BlogOperationServiceImpl(BlogOperationMapper blogOperationMapper, BlogPostService blogPostService) {
        this.blogOperationMapper = blogOperationMapper;
        this.blogPostService = blogPostService;
    }

    @Override
    public boolean add(BlogOperation blogOperation) {
        if (blogOperation.getOperationType() == 1) {
            blogOperationMapper.insertAll(blogOperation);
        } else {

            BlogOperation blogOperation1 = blogOperationMapper.selectByUserIdAndBlogIdBlogOperation(blogOperation.getBlogId(), blogOperation.getUserId());
            if (blogOperation1 != null) {
                blogOperation.setOldOperationType(blogOperation1.getOperationType());
                blogOperationMapper.updateOperationTypeByUserIdAndBlogIdInt(blogOperation);
                int nums = blogOperationMapper.selectByBlogIdBlogOperations(blogOperation.getBlogId(), 1);
                blogPostService.updateBrowseNumberByBlogIdInt(blogOperation.getBlogId(), nums);
                nums = blogOperationMapper.selectByBlogIdBlogOperations(blogOperation.getBlogId(), 2);
                blogPostService.updateGoodReviewByBlogIdInt(blogOperation.getBlogId(), nums);
                nums = blogOperationMapper.selectByBlogIdBlogOperations(blogOperation.getBlogId(), 3);
                blogPostService.updateBadReviewByBlogIdInt(blogOperation.getBlogId(), nums);
            } else {
                blogOperationMapper.insertAll(blogOperation);
            }
//            blogPostService.updateBadReviewByBlogIdInt(blogOperation.getBlogId(), blogOperationMapper.selectByBlogIdBlogOperations())
            return true;
        }
        return false;
    }

    @Override
    public List<BlogPost> findUserOperations(Long userId) {
        List<Long> longs = blogOperationMapper.selectByUserIdBlogOperations(userId);
        List<BlogPost> blogPosts = blogPostService.searchAllByBlogIds(longs);
        return blogPosts;
    }

    @Override
    public int selectByBlogIdBlogOperations(Long blogId, Integer operationType) {
        return blogOperationMapper.selectByBlogIdBlogOperations(blogId, operationType);
    }
}




