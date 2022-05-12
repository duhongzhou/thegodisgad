package top.thegodisgad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.utils.message.MessageUtils;

/**
 * @author yhdhz
 */
@RestController
public class BaseRestController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    protected final MessageUtils messageUtils;

    public BaseRestController(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }
}
