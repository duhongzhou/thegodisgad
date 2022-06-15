package top.thegodisgad.blog.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.blog.entity.BlogOperation;
import top.thegodisgad.blog.entity.BlogPost;
import top.thegodisgad.blog.service.BlogOperationService;
import top.thegodisgad.blog.service.BlogPostService;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yhdhz
 */
@RestController
@RequestMapping(value = {"/api/blog"})
public class BlogRestController extends BaseRestController {
    BlogPostService blogPostService;
    BlogOperationService blogOperationService;
    RedisOperator redisOperator;

    @Autowired
    public BlogRestController(MessageUtils messageUtils, BlogPostService blogPostService, BlogOperationService blogOperationService, RedisOperator redisOperator) {
        super(messageUtils);
        this.blogPostService = blogPostService;
        this.blogOperationService = blogOperationService;
        this.redisOperator = redisOperator;
    }


    @RequestMapping(value = "/operation/user")
    @ResponseBody
    public Response operation_user(HttpServletRequest request) {
        List<BlogPost> userOperations = blogOperationService.findUserOperations(redisOperator.getUser(request).getUserId());
        return Response.succeed(userOperations, "请求成功");
    }

    @RequestMapping(value = "/operation/{type}/{isValid}")
    @ResponseBody
    public Response operation(@PathVariable Integer type, @PathVariable Integer isValid, @RequestParam(value = "blogId") Long blogId, HttpServletRequest request) {
        BlogOperation blogOperation = new BlogOperation();
        blogOperation.setBlogId(blogId);
        blogOperation.setOperationType(type);
        blogOperation.setUserId(redisOperator.getUser(request).getUserId());
        blogOperation.setIsValid(isValid == 1 ? 1 : 0);
        boolean add = blogOperationService.add(blogOperation);
        return Response.succeed(add, "操作成功");
    }

    @RequestMapping(value = "/view/{blogId}")
    @ResponseBody
    public Response view(@PathVariable final Long blogId, HttpServletRequest request) {

        List<BlogPost> blogPosts = blogPostService.searchAllByBlogId(blogId);
        if (blogPosts.size() == 0) {
            return Response.failed("没有");
        }
        return Response.succeed(blogPosts);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Response delete(@RequestParam(value = "blogId") Long blogId, HttpServletRequest request) {
        if (blogId != null && blogId != 0) {
            BlogPost blogPost = new BlogPost();
            blogPost.setBlogId(blogId);
            blogPost.setUserId(redisOperator.getUser(request).getUserId());
            int delete = blogPostService.delete(blogPost);
            return Response.succeed(blogId, "删除成功!");
        } else {
            return Response.succeed(blogId, "删除失败!");
        }
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(@Validated @RequestBody BlogPost blogPost, HttpServletRequest request) {
        if (blogPost.getParentalBlogId() == null) {
            blogPost.setParentalBlogId(0L);
            blogPost.setRootBlogId(0L);
        }
        blogPost.setUserId(redisOperator.getUser(request).getUserId());
        Long add = blogPostService.add(blogPost);
        return Response.succeed("http://localhost:8081/blog/show/?blogId=" + add, "添加成功!");
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public Response search(@RequestParam(value = "start") Integer start, @RequestParam(value = "length") Integer length, HttpServletRequest request) {
        if (start == null) {
            start = 0;
        }
        if (length == 0) {
            length = 10;
        }
        List<BlogPost> blogPosts = blogPostService.searchAll(start, length);
        Response succeed = Response.succeed(blogPosts, "请求成功");
        return succeed;
    }

    @RequestMapping(value = "/searchUser")
    @ResponseBody
    public Response searchUser(HttpServletRequest request) {
        Long userId = redisOperator.getUser(request).getUserId();
        return Response.succeed(blogPostService.searchAllByUserId(userId), "请求成功");
    }

}
