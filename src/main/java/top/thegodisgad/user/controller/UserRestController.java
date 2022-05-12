package top.thegodisgad.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.config.SessionAttributeName;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.entity.UserInformationLog;
import top.thegodisgad.user.service.UserAuthorizedService;
import top.thegodisgad.user.service.UserInformationLogService;
import top.thegodisgad.user.service.UserService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @Autowired
    public UserRestController(MessageUtils messageUtils, UserService userService, UserAuthorizedService userAuthorizedService, UserInformationLogService userInformationLogService) {
        super(messageUtils);
        this.userService = userService;
        this.userAuthorizedService = userAuthorizedService;
        this.userInformationLogService = userInformationLogService;
    }




    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    /**
     * sign_out
     *
     * @param request
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: sign_out
     * @date 2022/5/23 15:00
     * @throw
     */
    @RequestMapping(value = "/sign_out")
    @ResponseBody
    public Response sign_out(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttributeName.USER_AUTHORIZED) != null) {
            session.removeAttribute(SessionAttributeName.USER_AUTHORIZED);
        } else {
            return Response.failed(messageUtils.getMessage("user.sign_out.fail.no_login"));
        }
        return Response.failed(null, messageUtils.getMessage("user.sign_out.success"));
    }
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    @RequestMapping(value = "/updateInformation")
    @ResponseBody
    public Response updateInformation(@RequestBody UserInformationLog userInformationLog, HttpServletRequest request) throws Exception {
        Response succeed = null;
        UserInformationLog sessionUserInformationLog = new UserInformationLog();
        HttpSession session = request.getSession();
        UserAuthorized sessionAuthorized = (UserAuthorized) session.getAttribute(SessionAttributeName.USER_AUTHORIZED);
        User sessionUser = (User) session.getAttribute(SessionAttributeName.USER);
        sessionUserInformationLog.setUser(null, sessionUser);
        sessionUserInformationLog.setUserAuthorized(sessionUser.getUserId(), sessionAuthorized);
        Response response = userInformationLogService.updateInformation(userInformationLog, sessionUserInformationLog,messageUtils);


        return response;
    }
    /*--------------------------------------------------------*/



}
