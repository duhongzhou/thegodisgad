package top.thegodisgad.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.thegodisgad.file.service.FileUploadService;
import top.thegodisgad.vo.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping(value = {"/upload"})
public class FileController {
    FileUploadService fileUploadService;
    @Autowired
    public FileController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Response upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        File files= new File("G:\\2022spring\\网络编程实践\\thegodisgad\\upload\\"+"6082868474564331825", Objects.requireNonNull(file.getOriginalFilename()));
        if(!files.exists())
        {
            files.mkdirs();
        }
        try {
            file.transferTo(files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Response.succeed("ok","上传文件成功!");
    }
}
