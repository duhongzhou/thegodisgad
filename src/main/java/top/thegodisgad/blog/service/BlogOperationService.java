package top.thegodisgad.blog.service;

import top.thegodisgad.blog.entity.BlogOperation;
import top.thegodisgad.blog.entity.BlogPost;

import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_blog_operation(博客文章操作)】的数据库操作Service
 * @createDate 2022-05-19 16:17:38
 */
public interface BlogOperationService {
    boolean add(BlogOperation blogOperation);

    List<BlogPost>  findUserOperations(Long userId);

    int selectByBlogIdBlogOperations(Long blogId, Integer operationType);

}
