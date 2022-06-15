package top.thegodisgad.user.controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.config.SessionAttributeName;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserRelation;
import top.thegodisgad.user.service.UserRelationService;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yhdhz
 */
@RestController
@RequestMapping(value = "/api/relation")
public class UserRestRelationController {
    UserRelationService userRelationService;

    @Autowired
    public UserRestRelationController(UserRelationService userRelationService) {
        this.userRelationService = userRelationService;
    }
    /*--------------------------------------------------------*/

    @RequestMapping(value = "/attention")
    @ResponseBody
    public Response attention(@RequestParam(name = "friendId") Long friendId, HttpServletRequest request) {
        Long userId = ((User) request.getSession().getAttribute(SessionAttributeName.USER)).getUserId();
        UserRelation userRelation = new UserRelation();
        userRelation.setUserId(userId);
        userRelation.setFriendId(friendId);
        userRelationService.attention(userRelation);
        //TODO:国际化
        return Response.succeed(userRelation, "关注成功");
    }
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    @RequestMapping(value = "/cancel_attention")
    @ResponseBody
    public Response cancelAttention(HttpServletRequest request, @RequestParam(name = "friendId") Long friendId) {
        Long userId = ((User) request.getSession().getAttribute(SessionAttributeName.USER)).getUserId();
        UserRelation userRelation = new UserRelation();
        userRelation.setUserId(userId);
        userRelation.setFriendId(friendId);
        userRelationService.cancelAttention(userRelation);
//TODO:国际化
        return Response.succeed(userRelation, "取消关注成功");
    }
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    @RequestMapping(value = "/pull_black")
    @ResponseBody
    public Response pullBlack(HttpServletRequest request, @RequestParam(name = "friendId") Long friendId) {
        Long userId = ((User) request.getSession().getAttribute(SessionAttributeName.USER)).getUserId();
        UserRelation userRelation = new UserRelation();
        userRelation.setUserId(userId);
        userRelation.setFriendId(friendId);
        userRelationService.pullBlack(userRelation);
//TODO:国际化
        return Response.succeed(userRelation, "拉黑成功");
    }
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    @RequestMapping(value = "/cancel_pull_black")
    @ResponseBody
    public Response cancelPullBlack(HttpServletRequest request, @RequestParam(name = "friendId") Long friendId) {
        Long userId = ((User) request.getSession().getAttribute(SessionAttributeName.USER)).getUserId();
        UserRelation userRelation = new UserRelation();
        userRelation.setUserId(userId);
        userRelation.setFriendId(friendId);
        userRelationService.cancelPullBlack(userRelation);
        //TODO:国际化
        return Response.succeed(userRelation, "取消拉黑成功");
    }
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    @RequestMapping(value = "/get_relationship")
    @ResponseBody
    public Response getRelationship(HttpServletRequest request) {
        Long userId = ((User) request.getSession().getAttribute(SessionAttributeName.USER)).getUserId();
        UserRelation userRelation = new UserRelation();
        userRelation.setUserId(userId);

        List<UserRelation> relationship = userRelationService.getRelationship(userRelation);
        return Response.succeed(relationship, "共有" + relationship.size() + "关系");
    }
    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * changRelation
     *
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: TODO:changRelation
     * @date 2022/5/23 20:09
     * @throw
     */
    @RequestMapping(value = "/chang_relation")
    @ResponseBody
    public Response changRelation(HttpServletRequest request,
                                  @RequestParam(name = "friendId") Long friendId,
                                  @RequestParam(name = "isShield") int isShield) {
        Long userId = ((User) request.getSession().getAttribute(SessionAttributeName.USER)).getUserId();
        UserRelation userRelation = new UserRelation();
        userRelation.setUserId(userId);
        userRelation.setFriendId(friendId);
        userRelation.setIsShield(isShield);
        userRelationService.changRelation(userRelation);
        return Response.succeed(userRelation, "更改关系成功");
    }
    /*--------------------------------------------------------*/

}
