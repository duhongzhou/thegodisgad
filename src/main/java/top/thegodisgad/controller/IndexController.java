package top.thegodisgad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.thegodisgad.config.SessionAttributeName;
import top.thegodisgad.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yhdhz
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/","#","/index"})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        User sessionUser =(User) session.getAttribute(SessionAttributeName.USER);
        model.addAttribute("user",sessionUser);
        return "index";
    }
}
