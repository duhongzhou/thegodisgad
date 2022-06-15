package top.thegodisgad.tag.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.tag.entity.UserTag;
import top.thegodisgad.tag.mapper.UserTagMapper;
import top.thegodisgad.tag.service.TagService;
import top.thegodisgad.tag.service.UserTagService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yhdhz
 * @description 针对表【tb_user_tag(拥有的标签(所有标签))】的数据库操作Service实现
 * @createDate 2022-05-19 16:16:44
 */
@Service
public class UserTagServiceImpl implements UserTagService {
    TagService tagService;
    UserService userService;
    UserTagMapper userTagMapper;

    @Autowired
    public UserTagServiceImpl(TagService tagService, UserTagMapper userTagMapper, UserService userService) {
        this.tagService = tagService;
        this.userTagMapper = userTagMapper;
        this.userService = userService;
    }


    @Override
    public boolean add(UserTag userTag, Tag tag) {
        Long tagIdAndNullCreate = tagService.findTagIdAndNullCreate(tag);
        userTag.setTagId(tagIdAndNullCreate);
        boolean b = userTagMapper.insert(userTag) == 1;
        return b;
    }

    @Override
    public boolean delete(UserTag userTag) {
        boolean b = userTagMapper.delete(userTag) == 1;
        return b;
    }

    @Override
    public List<Tag> findByUserId(Long userId) {
        List<UserTag> blogTags = userTagMapper.selectByUserId(userId);
        if (blogTags.size() == 0) {
            return null;
        }
        List<Long> tagIds = blogTags.stream().map(UserTag::getTagId).collect(Collectors.toList());
        List<Tag> tags = tagService.findTagByTagIds(tagIds);
        return tags;
    }

    @Override
    public List<User> findByTagId(Long tagId) {
        List<Tag> tagByTagId = tagService.findTagByTagId(tagId);
        if (tagByTagId.size() == 0) {
            return null;
        }
        List<Long> tagIds = tagByTagId.stream().map(Tag::getTagId).collect(Collectors.toList());
        List<User> byUserIds = userService.findByUserIds(tagIds);
        return byUserIds;
    }
}




