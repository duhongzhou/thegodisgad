package top.thegodisgad.blog.cons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.blog.entity.BlogPost;
import top.thegodisgad.blog.service.BlogOperationService;
import top.thegodisgad.blog.service.BlogPostService;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/blog/serch")
public class BlogController {
    BlogPostService blogPostService;
    BlogOperationService blogOperationService;
    RedisOperator redisOperator;

    @Autowired
    public BlogController(BlogPostService blogPostService, BlogOperationService blogOperationService, RedisOperator redisOperator) {
        this.blogPostService = blogPostService;
        this.blogOperationService = blogOperationService;
        this.redisOperator = redisOperator;
    }

    @RequestMapping(value = "/view")
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
}
