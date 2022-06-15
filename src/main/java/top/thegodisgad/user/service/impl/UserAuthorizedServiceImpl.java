package top.thegodisgad.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.entity.UserLoginLog;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;
import top.thegodisgad.user.mapper.UserAuthorizedMapper;
import top.thegodisgad.user.service.UserAuthorizedService;
import top.thegodisgad.user.service.UserLoginLogService;
import top.thegodisgad.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author yhdhz
 * @description 针对表【tb_user_authorized(用户授权)】的数据库操作Service实现
 * @createDate 2022-05-19 16:17:06
 */
@Service
public class UserAuthorizedServiceImpl
        implements UserAuthorizedService {
    private final UserAuthorizedMapper userAuthorizedMapper;
    private final UserLoginLogService userLoginLogService;

    @Autowired
    public UserAuthorizedServiceImpl(UserAuthorizedMapper userAuthorizedMapper, UserLoginLogService userLoginLogService) {
        this.userAuthorizedMapper = userAuthorizedMapper;
        this.userLoginLogService = userLoginLogService;
    }

    /**
     * @param identifier
     * @param oldCertificate
     * @return
     */
    @Override
    public boolean verifyOldCertificate(String identifier, String oldCertificate) {
        UserAuthorized userAuthorized = userAuthorizedMapper.selectAllByIdentifier(identifier);
        return userAuthorized.getCertificate().equals(oldCertificate);
    }

    /**
     * @return boolean
     * @description: login
     * @author 杜洪洲
     * @date 2022/5/22 15:35
     * @throw
     */
    @Override
    public Long login(UserAuthorized userAuthorized, HttpServletRequest request) {
        if (userAuthorized == null) {
            throw new UserException(UserExceptionEnum.UNKNOWN);
        }
        UserAuthorized userAuthorized1 = userAuthorizedMapper.selectAllByIdentifier(userAuthorized.getIdentifier());
        if (userAuthorized1 == null) {
            throw new UserException(UserExceptionEnum.IDENTIFIER_NO_REGISTERED);
        }
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setLoginIp(RequestUtil.getIp(request));
        userLoginLog.setCertificate(userAuthorized.getCertificate());
        userLoginLog.setIdentifier(userAuthorized.getIdentifier());

        if (userAuthorized1.getCertificate().equalsIgnoreCase(userAuthorized.getCertificate())) {
            userLoginLog.setLoginStatus(1);
            userLoginLogService.login(userLoginLog);
            return userAuthorized1.getUserId();
        } else {
            userLoginLog.setLoginStatus(2);
            userLoginLogService.login(userLoginLog);
            throw new UserException(UserExceptionEnum.ERROR_CERTIFICATE);
        }

    }

    /**
     * @return boolean
     * @description: registered
     * @author 杜洪洲
     * @date 2022/5/22 15:37
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registered(UserAuthorized userAuthorized) throws UserException {
        if (userAuthorized.getCertificate() == null) {
            throw new UserException(UserExceptionEnum.CERTIFICATE_ISEMPTY);
        } else {
            if (userAuthorized.getCertificate().isEmpty()) {
                throw new UserException(UserExceptionEnum.CERTIFICATE_ISEMPTY);

            } else {
                int insert = userAuthorizedMapper.insert(userAuthorized);
                return true;

            }
        }

    }

    /**
     * @return boolean
     * @description: changeCertificate
     * @author 杜洪洲
     * @date 2022/5/22 15:37
     * @throw
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeCertificate(UserAuthorized userAuthorized) throws SQLIntegrityConstraintViolationException {
        Long userId = userAuthorized.getUserId();
        String certificate = userAuthorized.getCertificate();
        int i = userAuthorizedMapper.updateCertificateByUserId(userId, certificate);
        return i == 1;
    }

    /**
     * @return boolean
     * @description: logout
     * @author 杜洪洲
     * @date 2022/5/22 15:39
     * @throw
     */
    @Override
    public boolean logout(UserAuthorized userAuthorized) {
        Integer isValid = userAuthorized.getIsValid();
        Long userId = userAuthorized.getUserId();
        if (isValid != null) {
            isValid = 0;
        }
        if (userId == null || userId == 0) {
            throw new UserException(UserExceptionEnum.USERID_ISEMPTY);
        }
        userAuthorizedMapper.updateIsValidByUserId(userId, isValid);
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long getUserId(String identifier) {
        return userAuthorizedMapper.selectUserIdByIdentifier(identifier);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserAuthorized findCertificateByUserId(Long userId) {
        UserAuthorized userAuthorized = userAuthorizedMapper.selectCertificateByUserId(userId);
        return userAuthorized;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registeredOut(Long userId) {
        return userAuthorizedMapper.deletByUserId(userId) == 1;
    }
}




