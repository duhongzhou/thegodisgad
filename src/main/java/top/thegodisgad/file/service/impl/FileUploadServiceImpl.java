package top.thegodisgad.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.thegodisgad.config.FileConfig;
import top.thegodisgad.file.entity.FileUpload;
import top.thegodisgad.file.exception.FileException;
import top.thegodisgad.file.exception.FileExceptionEnum;
import top.thegodisgad.file.mapper.FileUploadMapper;
import top.thegodisgad.file.service.FileRequestLogService;
import top.thegodisgad.file.service.FileUploadService;
import top.thegodisgad.utils.IDUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author yhdhz
 * @description 针对表【tb_file_upload(图片)】的数据库操作Service实现
 * @createDate 2022-05-19 16:14:00
 */
@Service
public class FileUploadServiceImpl
        implements FileUploadService {
    FileRequestLogService fileRequestLogService;
    FileUploadMapper fileUploadMapper;

    @Autowired
    public FileUploadServiceImpl(FileRequestLogService fileRequestLogService, FileUploadMapper fileUploadMapper) {
        this.fileRequestLogService = fileRequestLogService;
        this.fileUploadMapper = fileUploadMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileUpload request(Long fileId, Long userId, String ip) {
        FileUpload fileUpload = fileUploadMapper.selectByFileId(fileId);
        fileRequestLogService.request(fileUpload.getFileRequestLog(userId, ip));
        return fileUpload;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long upload(FileUpload fileUpload) {
        MultipartFile file1 = fileUpload.getFile();
        Long id = IDUtils.createId();
        File file = FileConfig.save(fileUpload.getUserId() + "\\" + file1.getOriginalFilename());
        if (!file.exists()) {
            file.mkdirs();
        }
        fileUpload.setFileSize(file1.getSize());
        try {
            file1.transferTo(file);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.UPLOAD_FILE_FAIl);
        }

        fileUpload.setFileFormat(file1.getContentType());
        fileUpload.setFileUrl(file.getPath());
        fileUpload.setFileId(id);
        int insert = fileUploadMapper.insert(fileUpload);
        return insert == 1 ? id : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long fileId) {
        int delete = fileUploadMapper.delete(fileId);
        return delete == 1;
    }
}




