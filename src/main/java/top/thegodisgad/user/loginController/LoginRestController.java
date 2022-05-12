package top.thegodisgad.user.loginController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.config.SessionAttributeName;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.entity.UserInformationLog;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;
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
public class LoginRestController extends BaseRestController {
    private final UserService userService;
    private final UserAuthorizedService userAuthorizedService;
    private final UserInformationLogService userInformationLogService;

    @Autowired
    public LoginRestController(MessageUtils messageUtils, UserService userService, UserAuthorizedService userAuthorizedService, UserInformationLogService userInformationLogService) {
        super(messageUtils);
        this.userService = userService;
        this.userAuthorizedService = userAuthorizedService;
        this.userInformationLogService = userInformationLogService;
    }

    /**
     * loginPrivate
     *
     * @param userAuthorized
     * @param request
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: 不将这部分写到service是因为会导致service的复用
     * @date 2022/5/24 16:36
     * @throw
     */
    private Response loginPrivate(UserAuthorized userAuthorized, HttpServletRequest request) throws UserException {
        HttpSession session = request.getSession();
        UserAuthorized sessionAuthorized = (UserAuthorized) session.getAttribute(SessionAttributeName.USER_AUTHORIZED);
        User sessionUser = (User) session.getAttribute(SessionAttributeName.USER);
        Response succeed = null;
        if (userAuthorized != null) {
            boolean flag = true;
            if (userAuthorized.getIdentifier() == null) {
                if (userAuthorized.getIdentifier().isEmpty()) {
                    flag = false;
                } else {
                    throw new UserException(UserExceptionEnum.IDENTIFIER_ISEMPTY);
                }
            }
            if (userAuthorized.getCertificate() == null) {
                if (userAuthorized.getCertificate().isEmpty()) {
                    flag = false;
                } else {
                    throw new UserException(UserExceptionEnum.CERTIFICATE_ISEMPTY);
                }
            }
            if (flag) {
                if (sessionAuthorized != null) {
                    if (sessionAuthorized.getIdentifier().equalsIgnoreCase(userAuthorized.getIdentifier())) {
                        succeed = Response.failed(sessionUser, messageUtils.getMessage("user.login.repeat_login"));
                    } else {
                        succeed = Response.failed(sessionUser, messageUtils.getMessage("user.login.sign_out"));
                    }
                } else {
                    Long login = userAuthorizedService.login(userAuthorized, request);
                    userAuthorized.setUserId(login);
                    sessionUser = userService.getInformation(login);
                    session.setAttribute(SessionAttributeName.USER, sessionUser);
                    session.setAttribute(SessionAttributeName.USER_AUTHORIZED, userAuthorized);
                    succeed = Response.succeed(SessionAttributeName.USER, messageUtils.getMessage("user.login.success"));
                }
            }
        }
        return succeed;
    }
    /*--------------------------------------------------------*/

    @PostMapping(value = "/login/post")
    @ResponseBody
    public Response login(@RequestBody() UserAuthorized userAuthorized, HttpServletRequest request) {
        return loginPrivate(userAuthorized, request);
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Response login(@RequestParam(name = "identifier") String identifier, @RequestParam(name = "certificate") String certificate, HttpServletRequest request) {
        UserAuthorized userAuthorized = new UserAuthorized();
        userAuthorized.setIdentifier(identifier);
        userAuthorized.setCertificate(certificate);
        return loginPrivate(userAuthorized, request);
    }
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    @RequestMapping(value = "/registered")
    @ResponseBody
    public Response registered(@RequestBody @ModelAttribute UserInformationLog userInformationLog) {
        return  userInformationLogService.registered(userInformationLog);
    }
    /*--------------------------------------------------------*/


}
