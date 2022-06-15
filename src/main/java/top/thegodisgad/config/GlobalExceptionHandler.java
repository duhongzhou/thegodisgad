package top.thegodisgad.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    private final MessageUtils messageUtils;

    @Autowired
    public GlobalExceptionHandler(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Response MethodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        logger.info(e.getMessage());
        return Response.failed(e.getParameter(), e.getMessage());
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Response baseExceptionHandler(HttpServletRequest request, BaseException e) {
        logger.info(e.getMessage());
        Response response = Response.failed(e);
        String message = messageUtils.getMessage(response.getCode());
        if (message == null || message.isEmpty()) {
            message = e.getMessage();
        }
        response.setMessage(message);
        return response;
    }


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Response missingServletRequestParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException e) {
        logger.info(e.getMessage());
        Response response = Response.failed(messageUtils.getMessage(e.getParameterName(), e.getParameterName() + "不能为空"));
        return response;
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Response bindExceptionHandler(HttpServletRequest request, BindException e) {
        logger.info(e.getMessage());
        Response response = Response.failed(messageUtils.getMessage(e.getMessage(), e.toString()));
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest request, Exception e) {
        logger.info(e.toString());
        Response failed = Response.failed(e, "9999", "未处理的错误");
        logger.info(String.valueOf(e));
        return failed;
    }
}
