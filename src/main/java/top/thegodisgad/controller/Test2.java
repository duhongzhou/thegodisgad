package top.thegodisgad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.user.entity.UserAuthorized;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.user.exception.UserExceptionEnum;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yhdhz
 */
@RestController
@RequestMapping(value = "/test2")
public class Test2 extends BaseRestController {
    @Autowired
    public Test2(MessageUtils messageUtils) {
        super(messageUtils);
    }

    /*--------------------------------------------------------*/

    @RequestMapping(value = "/test")
    @ResponseBody
    public Response test(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Response response1 = new Response();
        response1.setCode("-1");
        response1.setMessage(messageUtils.getMessage("author.name"));
        response1.setResult(requestURI);

        return response1;
    }
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    @RequestMapping(value = "/test2")
    public Response test2(@RequestParam(name = "id") String id) {
        return Response.succeed(id);
    }

    /*--------------------------------------------------------*/
    /*--------------------------------------------------------*/

    @RequestMapping(value = "/exs")
    @ResponseBody
    public Response exs(@RequestParam(name = "id", required = false) String id) {
        if (id != null) {
            return Response.succeed(null, "ok");
        } else {
            throw new UserException(UserExceptionEnum.ERROR_USERID);
        }
    }
    /*--------------------------------------------------------*/


    /*--------------------------------------------------------*/

    @RequestMapping(value = "/test3")
    @ResponseBody
    public Response test3() {
        Map<String, Object> u = new HashMap<>(1);
        var user = new User();
        var us = new UserAuthorized();
        u.put("User", user);
        u.put("UserAuthorized", us);
        return Response.succeed(u);
    }
    /*--------------------------------------------------------*/

    /*--------------------------------------------------------*/

    @RequestMapping(value = "/test4")
    @ResponseBody
    public Response test4(@ModelAttribute User user, @ModelAttribute UserAuthorized userAuthorized) {

        System.out.println(user);
        System.out.println(userAuthorized);
        return Response.succeed(user);

    }
    /*--------------------------------------------------------*/
}
