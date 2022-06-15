package top.thegodisgad.school.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.aop.OnlyTeacher;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.school.entity.Academy;
import top.thegodisgad.school.group.SchoolGroup;
import top.thegodisgad.school.service.AcademyService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yhdhz
 */
@RestController
@RequestMapping(value = {"/api/academy"})
public class AcademyRestController extends BaseRestController {
    AcademyService academyService;

    @Autowired
    public AcademyRestController(MessageUtils messageUtils, AcademyService academyService) {
        super(messageUtils);
        this.academyService = academyService;
    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public Response update(@RequestBody Academy academy, HttpServletRequest request) {
        boolean update = academyService.update(academy);
        return Response.succeed("修改成功");
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    @OnlyTeacher
    public Response delete(@Validated(value = {SchoolGroup.add.class}) @ModelAttribute(value = "academyId") Long academyId, HttpServletRequest request) {
        boolean delete = academyService.delete(academyId);
        return Response.succeed(delete, "删除成功");
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(@RequestBody Academy academy, HttpServletRequest request) {
        Long add = academyService.add(academy);
        return Response.succeed(academy, "添加成功");
    }
}
