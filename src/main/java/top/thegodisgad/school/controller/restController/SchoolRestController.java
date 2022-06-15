package top.thegodisgad.school.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.school.entity.School;
import top.thegodisgad.school.entity.Teacher;
import top.thegodisgad.school.service.SchoolService;
import top.thegodisgad.school.service.TeacherService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = {"/api/school"})
public class SchoolRestController extends BaseRestController {
    SchoolService schoolService;
    RedisOperator redisOperator;
    TeacherService teacherService;

    @Autowired
    public SchoolRestController(MessageUtils messageUtils, SchoolService schoolService, RedisOperator redisOperator, TeacherService teacherService) {
        super(messageUtils);
        this.schoolService = schoolService;
        this.redisOperator = redisOperator;
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/all")
    @ResponseBody
    public Response all(HttpServletRequest request) {

        return Response.succeed(schoolService.findAll(), "请求成功");
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Response update(@RequestBody School school, HttpServletRequest request) {
        boolean update = schoolService.update(school);

        return Response.succeed(null, "修改成功");
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public Response get(HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        Teacher teacher = teacherService.findByUserId(user.getUserId());
        if (teacher != null) {
            School school = schoolService.findBySchoolId(teacher.getSchoolId());
            Response succeed = Response.succeed(school, "请求成功");
            return succeed;
        } else {
            return Response.failed("你还不是管理员");
        }
    }

    @RequestMapping(value = "/get_by_school_id")
    @ResponseBody
    public Response get_by_school_id(@RequestParam(value = "schoolId") Long schoolId, HttpServletRequest request) {
        School bySchoolId = schoolService.findBySchoolId(schoolId);
        return Response.succeed(bySchoolId, "请求成功");
    }
}
