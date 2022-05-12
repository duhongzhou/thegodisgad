package top.thegodisgad.user.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 信息
 * @TableName tb_message
 */
@Data
public class Message implements Serializable {
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
     * 发送人
     */
    private Long sender;

    /**
     * 接受人
     */
    @NotNull
    private Long accepter;


    /**
     * 是已读;1:已读;0:未读;
     */
    private Integer isRead;

    /**
     * 读取时间
     */
    private Date readTime;

    /**
     * 是撤回的;1:撤回;0:正常;
     */
    private Integer isRevocation;

    /**
     * 内容
     */
    @NotNull
    @Pattern(regexp = "[\\S\\s]+")
    private String value;

    /**
     * 是匿名的;1:是;0:不是;
     */
    private Integer isAnonymous;

    private static final long serialVersionUID = 1L;
}