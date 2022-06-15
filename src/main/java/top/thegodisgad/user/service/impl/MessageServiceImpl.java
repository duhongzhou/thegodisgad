package top.thegodisgad.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.user.entity.Message;
import top.thegodisgad.user.mapper.MessageMapper;
import top.thegodisgad.user.service.MessageService;
import top.thegodisgad.user.service.UserService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
* @author yhdhz
* @description 针对表【tb_message(信息)】的数据库操作Service实现
* @createDate 2022-05-19 16:19:59
*/
@Service
public class MessageServiceImpl 
    implements MessageService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MessageMapper messageMapper;
    private final UserService userService;
    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper, UserService userService) {
        this.messageMapper = messageMapper;
        this.userService = userService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean sendMessage(Message message) throws SQLIntegrityConstraintViolationException {

        return messageMapper.insert(message) == 1;

    }

    @Override
    public List<Message> findMessages(Long userId) {
        List<Message> messages = messageMapper.selectByAcceptor(userId);
        messages.addAll(messageMapper.selectBySender(userId));
        return messages;
    }

    @Override
    public void read(Long userId, List<Long> messageIds) {
        messageMapper.updateForIsReadByMessageId(userId, messageIds);
    }
}




