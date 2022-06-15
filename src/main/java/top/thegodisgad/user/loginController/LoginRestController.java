package top.thegodisgad.user.loginController;

import com.alibaba.fastjson2.JSON;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import top.thegodisgad.utils.RequestUtil;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.utils.token.TokenUtil;
import top.thegodisgad.vo.response.Response;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RedisOperator redisOperator;
    private Producer captchaProducer;

    @Autowired
    public LoginRestController(MessageUtils messageUtils, UserService userService, UserAuthorizedService userAuthorizedService, UserInformationLogService userInformationLogService, RedisOperator redisOperator, Producer captchaProducer) {
        super(messageUtils);
        this.userService = userService;
        this.userAuthorizedService = userAuthorizedService;
        this.userInformationLogService = userInformationLogService;
        this.redisOperator = redisOperator;
        this.captchaProducer = captchaProducer;
    }


    /**
     * loginPrivate
     *
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
                    succeed = Response.succeed(sessionUser, messageUtils.getMessage("user.login.success"));
                }
            }
        }
        return succeed;
    }
    /*--------------------------------------------------------*/

    @PostMapping(value = "/login/post")
    @ResponseBody
    public Response login_post(@RequestBody UserAuthorized userAuthorized, HttpServletRequest request)
            throws UnsupportedEncodingException {
        Response response = loginPrivate(userAuthorized, request);
        if (response.getCode().equals("-1")) {
            User result = (User) response.getResult();
            userAuthorized.setUserId(result.getUserId());
            String s = TokenUtil.generateToken(userAuthorized);
            redisOperator.set(s, JSON.toJSONString(result), 60 * 60);
            return Response.succeed(s, "登录成功");
        } else {
            return response;
        }

    }
    /*--------------------------------------------------------*/

    /**
     * 用户名注册
     *
     * @param userInformationLog
     * @return
     */
    @PostMapping(value = "/registered")
    @ResponseBody
    public Response registered(@RequestBody UserInformationLog userInformationLog, HttpServletRequest request) {
        if (userInformationLog.getCertificate() == null) {
            return Response.failed("密码不能为空");
        }
        if (!userInformationLog.getCertificate().equals(userInformationLog.getOldCertificate())) {
            return Response.failed("两次输入入的密码不一致!");
        }
        userInformationLog.setRegisterSource(1);
        return userInformationLogService.registered(userInformationLog);
    }


    /**
     * ----------------------特别声明下面的不是我写的!!!----------------------------
     */
    /*--------------------------------------------------------*/
    @RequestMapping(value = "/verificationCode")
    public Response verificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String temporaryToken = request.getParameter("temporaryToken");
        logger.info("test1" + temporaryToken);
        if (temporaryToken == null) {
            return Response.failed("临时token为空");
        } else {

            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);

            // Set standard HTTP/1.1 no-cache headers.
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

            // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");

            // Set standard HTTP/1.0 no-cache header.
            response.setHeader("Pragma", "no-cache");

            // return a jpeg
            response.setContentType("image/jpeg");

            // create the text for the image
            String capText = captchaProducer.createText();
            String ip = RequestUtil.getIp(request);
            redisOperator.set(temporaryToken, capText, 60 * 60 * 5);
            Cookie cookie = new Cookie("verificationCode", ip);
            response.addCookie(cookie);
            // create the image with the text
            BufferedImage bi = captchaProducer.createImage(capText);
            ServletOutputStream out = response.getOutputStream();

            // write the data out
            ImageIO.write(bi, "jpg", out);
            try {
                out.flush();
            } finally {
                out.close();
            }
            return null;
        }
    }

    @RequestMapping(value = "/temporaryToken")
    @ResponseBody
    public Response temporaryToken(HttpServletRequest request) {
        String s = TokenUtil.temporaryToken(request);
        redisOperator.set(s, s, 5 * 60 * 1000);
        return Response.succeed(s, "临时token");
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Response test(@RequestBody Object o) {

        return Response.succeed(o, "ok");
    }
}
