package top.thegodisgad.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.entity.UserInformationLog;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;
import top.thegodisgad.user.mapper.UserInformationLogMapper;
import top.thegodisgad.user.service.UserAuthorizedService;
import top.thegodisgad.user.service.UserInformationLogService;
import top.thegodisgad.user.service.UserService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user_information_log(表用户信息修改日志注释)】的数据库操作Service实现
 * @createDate 2022-05-19 16:17:09
 */
@Service
public class UserInformationLogServiceImpl
        implements UserInformationLogService {
    private final UserAuthorizedService userAuthorizedService;
    private final UserService userService;
    private final UserInformationLogMapper userInformationLogMapper;
    private final MessageUtils messageUtils;

    @Autowired
    public UserInformationLogServiceImpl(UserAuthorizedService userAuthorizedService, UserService userService, UserInformationLogMapper userInformationLogMapper, MessageUtils messageUtils) {
        this.userAuthorizedService = userAuthorizedService;
        this.userService = userService;
        this.userInformationLogMapper = userInformationLogMapper;
        this.messageUtils = messageUtils;
    }

    /**
     * updateInformation
     *
     * @param userInformationLog
     * @param sessionUserInformationLog
     * @param messageUtils
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: TODO:updateInformation 权限控制
     * @date 2022/5/24 18:24
     * @throw
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response updateInformation(UserInformationLog userInformationLog, UserInformationLog sessionUserInformationLog, MessageUtils messageUtils) throws Exception {
        //
        Response response;
        if (!userService.updateInformation(userInformationLog.getUser())) {
            throw new UserException(UserExceptionEnum.UNKNOWN);
        } else {
            response = Response.succeed(sessionUserInformationLog.getUser(), "updateInformation.succeed");
        }
        //sessionUserInformationLog.getCertificate() 肯定不是null 反过来就不能更改信息,只能改密码
        if (sessionUserInformationLog.getCertificate().equalsIgnoreCase(userInformationLog.getOldCertificate())) {
            if (userInformationLog.getOldCertificate().equalsIgnoreCase(sessionUserInformationLog.getCertificate())) {
                userAuthorizedService.changeCertificate(userInformationLog.getUserAuthorized());
                response = Response.succeed(response, messageUtils.getMessage("updateInformation.changCertificate.succeed"));
            } else {
                throw new UserException(UserExceptionEnum.ERROR_CERTIFICATE);
            }

        } else {
            throw new UserException(UserExceptionEnum.UPDATE_ERROR_NO_OLD_CER);
        }
        if (userInformationLogMapper.insert(sessionUserInformationLog) == 1) {
            return response;
        } else {
            throw new UserException(UserExceptionEnum.UPDATE_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response registered(UserInformationLog userInformationLog) {
        User user = userInformationLog.getUser();
        Long registered = userService.registered(user);
        userInformationLog.setUserId(registered);
        UserAuthorized userAuthorized;
        userAuthorized = userInformationLog.getUserAuthorizedForName();
        if (userAuthorized!=null)
        {
            userAuthorizedService.registered(userAuthorized);
        }
        userAuthorized = userInformationLog.getUserAuthorizedForMobile();
        if (userAuthorized!=null)
        {
            userAuthorizedService.registered(userAuthorized);
        }
        userAuthorized = userInformationLog.getUserAuthorizedForEmail();
        if (userAuthorized!=null)
        {
            userAuthorizedService.registered(userAuthorized);
        }

        return Response.succeed(user, messageUtils.getMessage("registered.successfully"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response batchRegistered(List<UserInformationLog> userInformationLogs) {

        List<Object> users = new ArrayList<>();
        for (UserInformationLog userInformationLog : userInformationLogs) {
            Response registered = registered(userInformationLog);
            Object result = registered.getResult();
            users.add(result);
        }
        return Response.succeed(users, "registered.successfully");
    }
}




