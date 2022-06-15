package top.thegodisgad.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.thegodisgad.file.entity.FileRequestLog;
import top.thegodisgad.file.mapper.FileRequestLogMapper;
import top.thegodisgad.file.service.FileRequestLogService;

/**
* @author yhdhz
* @description 针对表【tb_file_request_log(请求文件)】的数据库操作Service实现
* @createDate 2022-05-19 16:14:06
*/
@Service
public class FileRequestLogServiceImpl 
    implements FileRequestLogService{
    FileRequestLogMapper fileRequestLogMapper;
    @Autowired
    public FileRequestLogServiceImpl(FileRequestLogMapper fileRequestLogMapper) {
        this.fileRequestLogMapper = fileRequestLogMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean request(FileRequestLog fileRequestLog) {
        return fileRequestLogMapper.insert(fileRequestLog)==1;
    }
}




