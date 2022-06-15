package top.thegodisgad.school.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.thegodisgad.aop.OnlyTeacher;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.school.entity.Academy;
import top.thegodisgad.school.entity.OpeningProfessional;
import top.thegodisgad.school.entity.Professional;
import top.thegodisgad.school.group.SchoolGroup;
import top.thegodisgad.school.service.OpeningProfessionalService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/openingProfessional"})
public class OpeningProfessionalRestCotroller extends BaseRestController {
    OpeningProfessionalService openingProfessionalService;

    @Autowired
    public OpeningProfessionalRestCotroller(MessageUtils messageUtils, OpeningProfessionalService openingProfessionalService) {
        super(messageUtils);
        this.openingProfessionalService = openingProfessionalService;
    }

    @RequestMapping(value = "/school")
    @ResponseBody
    public Response school(@RequestParam(value = "schoolId") Long schoolId, HttpServletRequest request) {
        List<Academy> academyBySchoolId = openingProfessionalService.findAcademyBySchoolId(schoolId);
        return Response.succeed(academyBySchoolId, "请求成功!");
    }

    @RequestMapping(value = "/have")
    @ResponseBody
    public Response have(@RequestParam(value = "professionalId") Long professionalId, HttpServletRequest request) {
        List<Academy> professionalAcademy = openingProfessionalService.findProfessionalAcademy(professionalId);
        return Response.succeed(professionalAcademy, "请求成功");
    }

    @RequestMapping(value = "/professionals")
    @ResponseBody
    public Response professionals(@RequestParam(value = "academyId") Long academyId, HttpServletRequest request) {
        List<Professional> academyProfessional = openingProfessionalService.findAcademyProfessional(academyId);
        return Response.succeed(academyProfessional, "请求成功");
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    @OnlyTeacher
    public Response delete(@Validated(value = SchoolGroup.delete.class) @RequestBody OpeningProfessional openingProfessional, HttpServletRequest request) {
        boolean delete = openingProfessionalService.delete(openingProfessional);
        return Response.succeed(delete, "删除成功!");
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(@Validated(value = SchoolGroup.add.class) @RequestBody OpeningProfessional openingProfessional, HttpServletRequest request) {
        Long add = openingProfessionalService.add(openingProfessional);
        return Response.succeed(add, "添加成功!");
    }
    
}
