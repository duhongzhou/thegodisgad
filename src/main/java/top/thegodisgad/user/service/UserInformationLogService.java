package top.thegodisgad.user.service;

import top.thegodisgad.school.entity.Student;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.entity.UserInformationLog;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_user_information_log(表用户信息修改日志注释)】的数据库操作Service
 * @createDate 2022-05-19 16:17:09
 */
public interface UserInformationLogService {
    /*--------------------------------------------------------*/

    /**
     * updateInformation
     *
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: updateInformation
     * @date 2022/5/24 17:15
     * @throw
     */
    public Response updateInformation(UserInformationLog userInformationLog, UserInformationLog sessionUserInformationLog, MessageUtils messageUtils) throws Exception;

    /**
     * 修改信息
     *
     * @param user
     * @return
     */
    public User updateInformation(User user);
    /*--------------------------------------------------------*/

    /**
     * 修改密码
     *
     * @param userAuthorized
     * @return
     */
    public Response updateCertificate(String oldCertificate, UserAuthorized userAuthorized) throws SQLIntegrityConstraintViolationException;

    /**
     * registered
     *
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: registered
     * @date 2022/5/24 19:37
     */
    public Response registered(UserInformationLog userInformationLog);

    /**
     * batchRegistered
     *
     * @return top.thegodisgad.vo.response.Response
     * @author 杜洪洲
     * @description: batchRegistered
     * @date 2022/5/24 19:37
     */
    public List<User> batchRegistered(List<UserInformationLog> userInformationLogs);


    User selectByUserId(Long userId);

    List<Student> findStudentsNameAndCertificate(List<Student> students);

    void registeredOut(Long userId);
}
