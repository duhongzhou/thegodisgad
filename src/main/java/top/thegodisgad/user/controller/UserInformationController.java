package top.thegodisgad.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/view/user/information")
public class UserInformationController {

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request, HttpServletResponse response, Model model) {
        //TODO update/information
        return "/user/updateInformation";
    }

    @RequestMapping(value = "/show/{userId}")
    public String show(@PathVariable Long userId, HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(SessionAttributeName.USER);
        if (sessionUser.getUserId().equals(userId)) {
            //TODO:查询用户信息;
        } else {
            model.addAttribute("user", sessionUser);
        }
        return "/user/showInformation";
    }
}
