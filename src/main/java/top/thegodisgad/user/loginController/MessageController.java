package top.thegodisgad.user.loginController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
    @RequestMapping(value = "/send")
    public String send(HttpServletRequest request, HttpServletResponse response, Model model) {
        
        return "send";
    }
}
