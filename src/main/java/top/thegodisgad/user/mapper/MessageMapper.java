package top.thegodisgad.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.user.entity.Message;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_message(信息)】的数据库操作Mapper
* @createDate 2022-05-19 16:19:59
* @Entity top.thegodisgad.user.entity.Message
*/
@Repository
@Mapper
public interface MessageMapper  {
    /*--------------------------------------------------------*/
    /**
     * insert
     *
     * @author 杜洪洲
     * @description: TODO:insert
     * @date 2022/5/24 20:57
     * @param message
     * @return int
     * @throws
     */
    public int insert(Message message) throws SQLIntegrityConstraintViolationException;
    /*--------------------------------------------------------*/
    /**
     * selectBySenderAndAcceptor
     *
     * @author 杜洪洲
     * @description: TODO:selectBySenderAndAcceptor
     * @date 2022/5/24 20:59
     * @param sender
     * @param accepter
     * @return java.util.List<top.thegodisgad.user.entity.Message>
     * @throws
     */
    public List<Message> selectBySenderAndAcceptor(@Param("sender") Long sender,@Param("accepter") Long accepter);


    public List<Message> selectBySender(@Param("sender") Long sender);

    public List<Message> selectByAcceptor(@Param("accepter") Long accepter);

    public int updateForIsReadByMessageId(@Param("accepter") Long accepter,@Param(value = "senders") List<Long> senders);
}




