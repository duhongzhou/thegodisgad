package top.thegodisgad.file.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图片
 * @TableName tb_file_upload
 */
@Data
public class FileUpload implements Serializable {
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
    private Integer fileSize;

    /**
     * 是有效的;1:有效;0:无效;
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;
}