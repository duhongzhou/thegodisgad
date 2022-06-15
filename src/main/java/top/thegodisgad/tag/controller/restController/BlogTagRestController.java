package top.thegodisgad.tag.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.blog.entity.BlogPost;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.tag.entity.BlogTag;
import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.tag.group.TagGroup;
import top.thegodisgad.tag.service.BlogTagService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/tag/blog"})
public class BlogTagRestController extends BaseRestController {
    BlogTagService blogTagService;

    @Autowired
    public BlogTagRestController(MessageUtils messageUtils, BlogTagService blogTagService) {
        super(messageUtils);
        this.blogTagService = blogTagService;
    }
     @RequestMapping(value = "/blogs")
      @ResponseBody
      public Response  blogs(@ModelAttribute(value = "tagId") Long tagId, HttpServletRequest request){
         List<BlogPost> byTagId = blogTagService.findByTagId(tagId);
         return Response.succeed(byTagId, "有" + byTagId.size() + "条博客使用此标签");
     }
     @RequestMapping(value = "/have")
      @ResponseBody
      public Response  have(@ModelAttribute(value = "blogId")Long blogId, HttpServletRequest request){
         List<Tag> byBlogId = blogTagService.findByBlogId(blogId);
         return Response.succeed(byBlogId, "此博客有" + byBlogId.size() + "个标签!");
     }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Response delete(@Validated(value = {TagGroup.Delete.class}) @ModelAttribute BlogTag blogTag, HttpServletRequest request) {
        if (blogTag.getBlogId() <= 0 || blogTag.getTagId() <= 0) {
            return Response.failed(blogTag, "错误的");
        }
        int delete = blogTagService.delete(blogTag);

        return Response.succeed(blogTag, "删除成功!");
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(@RequestParam(value = "blogId") Long blogId, @Validated(value = {TagGroup.Add.class}) Tag tag, HttpServletRequest request) {
        BlogTag blogTag = new BlogTag();
        blogTag.setBlogId(blogId);
        blogTagService.add(blogTag, tag);
        return Response.succeed(tag, "添加成功");
    }
}
