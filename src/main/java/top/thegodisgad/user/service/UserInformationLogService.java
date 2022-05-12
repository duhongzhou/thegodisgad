package top.thegodisgad.user.service;

import top.thegodisgad.user.entity.UserInformationLog;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_user_information_log(表用户信息修改日志注释)】的数据库操作Service
* @createDate 2022-05-19 16:17:09
*/
public interface UserInformationLogService  {
    /*--------------------------------------------------------*/
    /**
     * updateInformation
     *
     * @author 杜洪洲
     * @description: TODO:updateInformation
     * @date 2022/5/24 17:15
     * @param userInformationLog
     * @param sessionUserInformationLog
     * @param messageUtils
     * @return top.thegodisgad.vo.response.Response
     * @throw
     */
    public Response updateInformation(UserInformationLog userInformationLog, UserInformationLog sessionUserInformationLog, MessageUtils messageUtils) throws Exception;
    /*--------------------------------------------------------*/
    /**
     * registered
     *
     * @author 杜洪洲
     * @description: TODO:registered
     * @date 2022/5/24 19:37
     * @param userInformationLog
     * @return top.thegodisgad.vo.response.Response
     * @throws
     */
    public Response registered(UserInformationLog userInformationLog) ;
    /**
     * batchRegistered
     *
     * @author 杜洪洲
     * @description: TODO:batchRegistered
     * @date 2022/5/24 19:37
     * @param userInformationLogs
     * @return top.thegodisgad.vo.response.Response
     * @throws
     */
    public Response batchRegistered(List<UserInformationLog> userInformationLogs) ;


}
