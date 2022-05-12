package top.thegodisgad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.utils.RequestUtil;
import top.thegodisgad.utils.user.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RequestMapping(value = "/test")
@Controller
public class Test {
    @GetMapping(value = "/test1")
    public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        String ip = RequestUtil.getIP(request);
        writer.println(ip);
    }
    @GetMapping(value = "/testID")
    public void testID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("oo");
        PrintWriter writer = response.getWriter();
        writer.println(UserUtils.createId());
//        8 678 637 391 635 198 212
        //2 222 222 222 222 222
    }
    @GetMapping(value = "/test2")
    public String test2(Model model){
        return "index";
    }


    /*--------------------------------------------------------*/
    @RequestMapping(value = "/registereds")
    public String registered(@RequestBody(required = false) Map<UserAuthorized,User> u, HttpServletRequest request, HttpServletResponse response, Model model) {

        return "test/registered";
    }
    /*--------------------------------------------------------*/
}
