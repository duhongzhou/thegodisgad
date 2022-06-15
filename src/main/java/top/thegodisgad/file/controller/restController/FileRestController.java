package top.thegodisgad.file.controller.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.controller.BaseRestController;
import top.thegodisgad.file.entity.FileUpload;
import top.thegodisgad.file.service.FileUploadService;
import top.thegodisgad.utils.message.MessageUtils;
import top.thegodisgad.utils.token.RedisOperator;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = {"/api/file"})
public class FileRestController extends BaseRestController {
    public static final String IMAGE = "image";
    private static final Pattern PATTERN = Pattern.compile("^([\\S]*)/([\\S]*)$");
    FileUploadService fileUploadService;
    RedisOperator redisOperator;

    @Autowired
    public FileRestController(MessageUtils messageUtils, FileUploadService fileUploadService, RedisOperator redisOperator) {
        super(messageUtils);
        this.fileUploadService = fileUploadService;
        this.redisOperator = redisOperator;
    }


    @RequestMapping(value = "/upload")
    @ResponseBody
    public Response upload(@ModelAttribute FileUpload fileUpload, HttpServletRequest request) {
        fileUpload.setUserId(redisOperator.getUser(request).getUserId());
        Long fileId = fileUploadService.upload(fileUpload);
        return Response.succeed("http://10.90.246.218:8080/api/file/request/" + fileId, "上传文件成功!");
    }


}
