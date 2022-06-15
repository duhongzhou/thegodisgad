package top.thegodisgad.user.controller.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.config.SessionAttributeName;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.user.entity.Message;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.service.MessageService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author yhdhz
 */
@RestController
@RequestMapping(value = {"/api/message"})
public class MessageRestController extends BaseRestController {
    private final MessageService messageService;

    public MessageRestController(MessageUtils messageUtils, MessageService messageService) {
        super(messageUtils);
        this.messageService = messageService;
    }

    @RequestMapping(value = "/send")
    @ResponseBody
    public Response send(@RequestBody Message message) throws SQLIntegrityConstraintViolationException {
        messageService.sendMessage(message);
        return Response.succeed(message, messageUtils.getMessage("message.send.success"));
    }

    @RequestMapping(value = "/accept")
    @ResponseBody
    public Response accept(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SessionAttributeName.USER);
        List<Message> messages = messageService.findMessages(user.getUserId());
        return Response.succeed(messages, messageUtils.getMessage("accept.success"));
    }
     @RequestMapping(value = "/read")
      @ResponseBody
      public Response  read(@RequestBody List<Long> messageIds,HttpServletRequest request) {
         User user = (User) request.getSession().getAttribute(SessionAttributeName.USER);
            messageService.read(user.getUserId(),messageIds);
          return null;
      }
}
