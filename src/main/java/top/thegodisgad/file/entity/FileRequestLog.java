package top.thegodisgad.file.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求文件
 * @TableName tb_file_request_log
 */
@Data
public class FileRequestLog implements Serializable {
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
     * 用户标识
     */
    private Long userId;

    /**
     * 文件标识
     */
    private Long fileId;

    /**
     * 请求状态;1:成功;0:失败;
     */
    private Integer requestStatus;

    /**
     * 请求ip
     */
    private Object requestIp;

    private static final long serialVersionUID = 1L;
}