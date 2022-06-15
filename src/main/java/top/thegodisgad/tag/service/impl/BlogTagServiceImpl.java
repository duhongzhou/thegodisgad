package top.thegodisgad.tag.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.blog.entity.BlogPost;
import top.thegodisgad.blog.service.BlogPostService;
import top.thegodisgad.tag.entity.BlogTag;
import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.tag.mapper.BlogTagMapper;
import top.thegodisgad.tag.service.BlogTagService;
import top.thegodisgad.tag.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author yhdhz
* @description 针对表【tb_blog_tag(拥有的标签(所有标签))】的数据库操作Service实现
* @createDate 2022-05-19 16:16:02
*/
@Service
public class BlogTagServiceImpl 
    implements BlogTagService{
    TagService tagService;
    BlogTagMapper blogTagMapper;
    BlogPostService blogPostService;
    @Autowired
    public BlogTagServiceImpl(TagService tagService, BlogTagMapper blogTagMapper, BlogPostService blogPostService) {
        this.tagService = tagService;
        this.blogTagMapper = blogTagMapper;
        this.blogPostService = blogPostService;
    }




    @Override
    public int add(BlogTag blogTag, Tag tag) {
        Long tagIdAndNullCreate = tagService.findTagIdAndNullCreate(tag);
        blogTag.setTagId(tagIdAndNullCreate);
        int insert = blogTagMapper.insert(blogTag);
        return insert;
    }

    @Override
    public int delete(BlogTag blogTag) {
        int delete = blogTagMapper.delete(blogTag);
        return delete;
    }

    @Override
    public List<Tag> findByBlogId(Long blogId) {
        List<BlogTag> blogTags = blogTagMapper.selectByBlogId(blogId);
        List<Long> tagIds=blogTags.stream().map(BlogTag::getTagId).collect(Collectors.toList());
        List<Tag> tags = tagService.findTagByTagIds(tagIds);
        return tags;
    }

    @Override
    public List<BlogPost> findByTagId(Long tagId) {


        //博客通过博客id获取博客
        List<BlogTag> blogTags = blogTagMapper.selectByTagId(tagId);
        List<Long> blogIds = blogTags.stream().map(BlogTag::getBlogId).collect(Collectors.toList());
        List<BlogPost> blogPosts = blogPostService.searchAllByBlogIds(blogIds);
        return blogPosts;

    }
}




