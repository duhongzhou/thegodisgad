package top.thegodisgad.file.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片
 *
 * @author yhdhz
 * @TableName tb_file_upload
 */
@Data
public class FileUpload implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 自增标识,必为主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 用户标识(上传者)
     */
    private Long userId;
    /**
     * 文件标识
     */
    private Long fileId;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 文件格式
     */
    private String fileFormat;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;
    /**
     * 文件
     */
    private MultipartFile file;

    public FileRequestLog getFileRequestLog(Long userId, String ip) {
        FileRequestLog fileRequestLog = new FileRequestLog();
        fileRequestLog.setFileId(this.fileId);
        fileRequestLog.setRequestStatus(1);
        fileRequestLog.setRequestIp(ip);

        fileRequestLog.setUserId(userId);
        return fileRequestLog;
    }
}