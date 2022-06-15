package top.thegodisgad.file.service;

import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.file.entity.FileUpload;

/**
 * @author yhdhz
 * @description 针对表【tb_file_upload(图片)】的数据库操作Service
 * @createDate 2022-05-19 16:14:00
 */
public interface FileUploadService {


    @Transactional(rollbackFor = Exception.class)
    FileUpload request(Long fileId, Long userId, String ip);

    public Long upload(FileUpload fileUpload);


    public boolean delete(Long fileId);
}
