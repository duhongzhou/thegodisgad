package top.thegodisgad.school.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.school.entity.Student;
import top.thegodisgad.school.group.SchoolGroup;
import top.thegodisgad.school.service.StudentService;
import top.thegodisgad.user.entity.User;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/student"})
public class StudentRestController extends BaseRestController {
    StudentService studentService;
    RedisOperator redisOperator;

    @Autowired
    public StudentRestController(MessageUtils messageUtils, StudentService studentService, RedisOperator redisOperator) {
        super(messageUtils);
        this.studentService = studentService;
        this.redisOperator = redisOperator;
    }

    @RequestMapping(value = "/get_all_by_school_id")
    @ResponseBody
    public Response get_all_by_school_id(@RequestParam(value = "schoolId") Long schoolId, HttpServletRequest request) {
        List<Student> bySchoolId = studentService.findBySchoolId(schoolId);
        return Response.succeed(bySchoolId, "请求成功");
    }

    @RequestMapping(value = "/batch")
    @ResponseBody
    public Response batch(@Validated(value = {SchoolGroup.batch.class}) @RequestBody List<Student> students, HttpServletRequest request) {
        List<Long> longs = studentService.addStudents(students);

        return Response.succeed(longs, "批量添加成功!");
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(@RequestBody Student student, HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        student.setUserId(user.getUserId());
        Long add = studentService.add(student);
        return Response.succeed(add, "添加成功!");
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public Response show(HttpServletRequest request) {
        User user = redisOperator.getUser(request);
        List<Student> byUserId = studentService.findByUserId(user.getUserId());
        if (byUserId == null) {
            return Response.failed("没有教育经历!");
        }
        return Response.succeed(byUserId.get(0));
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Response delete(@RequestBody Student student, HttpServletRequest request) {
        boolean delete = studentService.delete(student);

        return Response.succeed(delete, "删除成功");
    }
}
