package top.thegodisgad.user.controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.entity.UserInformationLog;
import top.thegodisgad.user.service.UserInformationLogService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.utils.token.TokenUtil;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhdhz
 */
@RestController
@RequestMapping(value = "/api/user/information")
public class UserInformationRestController extends BaseRestController {
    UserInformationLogService userInformationLogService;
    RedisOperator redisOperator;

    @Autowired
    public UserInformationRestController(MessageUtils messageUtils, UserInformationLogService userInformationLogService, RedisOperator redisOperator) {
        super(messageUtils);
        this.userInformationLogService = userInformationLogService;
        this.redisOperator = redisOperator;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Response updateInformation(@RequestBody UserInformationLog userInformationLog, HttpServletRequest request) throws Exception {
        Response response = null;
        User user = redisOperator.getUser(request);
        String token = request.getParameter("token");
        String identifier = TokenUtil.get(token, "identifier");
        if (identifier.equals(user.getUserName())) {
            if (userInformationLog.getCertificate() != null) {

                //修改密码
                UserAuthorized userAuthorized = new UserAuthorized();
                userAuthorized.setUserId(user.getUserId());
                userAuthorized.setCertificate(userInformationLog.getCertificate());
                userAuthorized.setIdentifier(identifier);
                response = userInformationLogService.updateCertificate(userInformationLog.getOldCertificate(), userAuthorized);
                Boolean remove = redisOperator.remove(token);
            } else {
                // 修改信息
                User user1 = userInformationLog.getUser();
                user1.setUserId(user.getUserId());
                User user2 = userInformationLogService.updateInformation(user1);
                redisOperator.set(token, redisOperator.setUser(user2), 60 * 60);
                response = Response.succeed(user2, "修改成功");
            }
        } else {
            response = Response.failed("还想修改别人的信息");
        }

        return response;

    }


    /*--------------------------------------------------------*/

    @RequestMapping(value = {"/show"})
    @ResponseBody
    public Response show(HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        return Response.succeed(user, "请求成功");
    }

    @RequestMapping(value = {"/show/test/{userId}"})
    @ResponseBody
    public Response testshow(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {

        User user = userInformationLogService.selectByUserId(userId);

        return Response.succeed(user, "请求成功");
    }
}
