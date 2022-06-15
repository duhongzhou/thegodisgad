package top.thegodisgad.file.service;

import top.thegodisgad.file.entity.FileRequestLog;

/**
* @author yhdhz
* @description 针对表【tb_file_request_log(请求文件)】的数据库操作Service
* @createDate 2022-05-19 16:14:06
*/
public interface FileRequestLogService  {

    public boolean request(FileRequestLog fileRequestLog);
}
