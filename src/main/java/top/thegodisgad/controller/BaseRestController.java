package top.thegodisgad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.thegodisgad.utils.message.MessageUtils;

/**
 * @author yhdhz
 */

public class BaseRestController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    protected final MessageUtils messageUtils;

    public BaseRestController(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }
}
