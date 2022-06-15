package top.thegodisgad.school.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.school.entity.Teacher;
import top.thegodisgad.school.service.TeacherService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = {"/api/teacher"})
public class TeacherRestController extends BaseRestController {
    TeacherService teacherService;
    RedisOperator redisOperator;

    @Autowired
    public TeacherRestController(MessageUtils messageUtils, TeacherService teacherService, RedisOperator redisOperator) {
        super(messageUtils);
        this.teacherService = teacherService;
        this.redisOperator = redisOperator;
    }


    @RequestMapping(value = "/is")
    @ResponseBody
    public Response is(HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        Teacher byUserId = teacherService.findByUserId(user.getUserId());
        if (byUserId == null) {
            Response.failed("不是老师");
        }
        return Response.succeed(byUserId, "是是老师");
    }
}
