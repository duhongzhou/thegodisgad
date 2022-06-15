package top.thegodisgad.file.reques;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.thegodisgad.file.entity.FileUpload;
import top.thegodisgad.file.service.FileUploadService;
import top.thegodisgad.user.exception.UserException;
import top.thegodisgad.utils.RequestUtil;
import top.thegodisgad.utils.token.RedisOperator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = {"/api/file"})
public class FileRequestCon {
    public static final String IMAGE = "image";
    private static final Pattern PATTERN = Pattern.compile("^([\\S]*)/([\\S]*)$");
    FileUploadService fileUploadService;
    RedisOperator redisOperator;

    @Autowired
    public FileRequestCon(FileUploadService fileUploadService, RedisOperator redisOperator) {
        this.fileUploadService = fileUploadService;
        this.redisOperator = redisOperator;
    }

    @RequestMapping(value = "/request/{fileId}")
    public void request(@PathVariable() Long fileId, HttpServletRequest request, HttpServletResponse response) {
        Long userId;
        try {
            userId = redisOperator.getUser(request).getUserId();
        } catch (UserException e) {
            userId = 123456789L;
        }

        FileUpload file = fileUploadService.request(fileId, userId, RequestUtil.getIp(request));
        if (file != null) {
            File file1 = new File(file.getFileUrl());
            if (file1.exists()) {

                FileInputStream fileInputStream;
                ServletOutputStream servletOutputStream;
                try {

                    if (!file.getFileFormat().startsWith(IMAGE)) {
                        response.setHeader("Content-Type", "application/x-msdownload");
                        response.setHeader("Content-Disposition", "attachment;fileName=" + new String(file1.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                    }
                    fileInputStream = new FileInputStream(file1);
                    servletOutputStream = response.getOutputStream();
                    byte[] bytes1 = new byte[1024];
                    servletOutputStream.flush();
                    while (fileInputStream.read(bytes1) != -1) {
                        servletOutputStream.write(bytes1);
                    }
                    servletOutputStream.flush();
                    fileInputStream.close();
                    servletOutputStream.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        }


    }
}
