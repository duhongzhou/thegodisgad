package top.thegodisgad.user.loginController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/view/user")
public class UserController {
    /*--------------------------------------------------------*/
    @RequestMapping(value = "/registered")
    public String registered(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "user/registered";
    }
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "/user/login";
    }
    /*--------------------------------------------------------*/
}
