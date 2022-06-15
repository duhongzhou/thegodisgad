package top.thegodisgad.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.thegodisgad.file.entity.FileUpload;

/**
* @author yhdhz
* @description 针对表【tb_file_upload(图片)】的数据库操作Mapper
* @createDate 2022-05-19 16:14:00
* @Entity top.thegodisgad.file.entity.FileUpload
*/
@Repository
@Mapper
public interface FileUploadMapper  {
    public int insert(FileUpload fileUpload);

    public int delete(@Param(value = "fileId") Long fileId);

    public FileUpload selectByFileId(@Param(value = "fileId") Long fileId);
}




