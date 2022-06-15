package top.thegodisgad.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.thegodisgad.file.entity.FileRequestLog;

/**
* @author yhdhz
* @description 针对表【tb_file_request_log(请求文件)】的数据库操作Mapper
* @createDate 2022-05-19 16:14:06
* @Entity top.thegodisgad.file.entity.FileRequestLog
*/
@Repository
@Mapper
public interface FileRequestLogMapper  {
    int insert(FileRequestLog fileRequestLog);
}




