package top.thegodisgad.user.controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.service.UserAuthorizedService;
import top.thegodisgad.user.service.UserInformationLogService;
import top.thegodisgad.user.service.UserService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhdhz
 */
//@Validated
@RestController
@RequestMapping(value = {"/api/user"})
public class UserRestController extends BaseRestController {
    UserService userService;
    UserAuthorizedService userAuthorizedService;
    UserInformationLogService userInformationLogService;
    RedisOperator redisOperator;

    @Autowired
    public UserRestController(MessageUtils messageUtils, UserService userService, UserAuthorizedService userAuthorizedService, UserInformationLogService userInformationLogService, RedisOperator redisOperator) {
        super(messageUtils);
        this.userService = userService;
        this.userAuthorizedService = userAuthorizedService;
        this.userInformationLogService = userInformationLogService;
        this.redisOperator = redisOperator;
    }

    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * sign_out
     *
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: sign_out
     * @date 2022/5/23 15:00
     * @throw
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Response sign_out(HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        Boolean token = redisOperator.remove(request.getParameter("token"));
        if (token) {
            return Response.failed(null, messageUtils.getMessage("user.sign_out.success"));
        } else {
            return Response.failed(messageUtils.getMessage("user.sign_out.fail.no_login"));
        }

    }
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/


}
