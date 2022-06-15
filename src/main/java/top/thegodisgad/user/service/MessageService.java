package top.thegodisgad.user.service;

import top.thegodisgad.user.entity.Message;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author yhdhz
 * @description 针对表【tb_message(信息)】的数据库操作Service
 * @createDate 2022-05-19 16:19:59
 */
public interface MessageService {
    //发送信息
    //查询信息
    //保存消息
    //

    /*--------------------------------------------------------*/

    /**
     * sendMessage
     *
     * @return boolean
     * @author 杜洪洲
     * @description: sendMessage
     * @date 2022/5/24 20:51
     */
    public boolean sendMessage(Message message) throws SQLIntegrityConstraintViolationException;
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    /**
     * findMessages
     *
     * @author 杜洪洲
     * @description: findMessages
     * @date 2022/5/24 20:54
     * @return java.util.List<top.thegodisgad.user.entity.Message>
     */
    public List<Message> findMessages(Long userId);
    /**
     * read
     *
     * @author 杜洪洲
     * @description: read
     * @date 2022/5/24 22:08
     */
    void read(Long userId, List<Long> messageIds);
    /*--------------------------------------------------------*/

}
