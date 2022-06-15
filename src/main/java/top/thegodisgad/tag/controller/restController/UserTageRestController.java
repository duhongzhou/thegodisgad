package top.thegodisgad.tag.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.config.SessionAttributeName;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.tag.entity.Tag;
import top.thegodisgad.tag.entity.UserTag;
import top.thegodisgad.tag.group.TagGroup;
import top.thegodisgad.tag.service.UserTagService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/tag/user"})
public class UserTageRestController extends BaseRestController {
    UserTagService userTagService;
    RedisOperator redisOperator;

    @Autowired
    public UserTageRestController(MessageUtils messageUtils, UserTagService userTagService, RedisOperator redisOperator) {
        super(messageUtils);
        this.userTagService = userTagService;
        this.redisOperator = redisOperator;
    }


    @RequestMapping(value = "/users")
    @ResponseBody
    public Response users(@ModelAttribute(value = "tagId") Long tagId, HttpServletRequest request) {
        List<User> byTagId = userTagService.findByTagId(tagId);
        return Response.succeed(byTagId, "有" + byTagId.size() + "人使用了该标签!");
    }

    @RequestMapping(value = "/have")
    @ResponseBody
    public Response have(HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        return Response.succeed(userTagService.findByUserId(user.getUserId()));
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Response delete(@ModelAttribute(value = "tagId") Long tagId, HttpServletRequest request) {
        UserTag userTag = new UserTag();
        userTag.setUserId(SessionAttributeName.getUserId(request));
        userTag.setTagId(tagId);
        boolean delete = userTagService.delete(userTag);

        return Response.succeed(userTag, "删除成功!");
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(@Validated(value = TagGroup.Add.class) @RequestBody Tag tag, HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        UserTag userTag = new UserTag();
        userTag.setUserId(user.getUserId());
        tag.setCreatorId(userTag.getUserId());
        userTagService.add(userTag, tag);
        return Response.succeed(tag, "添加成功!");
    }

}
