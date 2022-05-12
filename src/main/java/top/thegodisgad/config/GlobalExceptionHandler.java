package top.thegodisgad.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.thegodisgad.exception.BaseException;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhdhz
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private MessageUtils messageUtils;

    @Autowired
    public GlobalExceptionHandler(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Response BaseExceptionHandler(HttpServletRequest request, BaseException e) {
        Response response = Response.failed(e);
        String message=messageUtils.getMessage(response.getCode());
        if(message==null || message.isEmpty())
        {
            message=e.getMessage();
        }
        response.setMessage(message);
        return response;
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Response BaseExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException e) {
        Response response = Response.failed(messageUtils.getMessage(e.getParameterName(),e.getParameterName()+"不能为空"));
        return response;
    }


}
