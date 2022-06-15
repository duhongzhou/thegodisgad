package top.thegodisgad.school.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.school.entity.Professional;
import top.thegodisgad.school.service.ProfessionalService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/professional"})
public class ProfessionalRestController extends BaseRestController {
    ProfessionalService professionalService;

    @Autowired
    public ProfessionalRestController(MessageUtils messageUtils, ProfessionalService professionalService) {
        super(messageUtils);
        this.professionalService = professionalService;
    }

    @RequestMapping(value = "/all")
    @ResponseBody
    public Response all(HttpServletRequest request) {
        List<Professional> professionals = professionalService.find();
        return Response.succeed(professionals, "请求成功!");
    }

    @RequestMapping(value = "/get_by_professional_id")
    @ResponseBody
    public Response get_by_professional_id(@RequestParam(value = "professionalId") Long professionalId, HttpServletRequest request) {
        Professional byProfessionalId = professionalService.findByProfessionalId(professionalId);
        return Response.succeed(byProfessionalId, "请求成功");
    }
}
