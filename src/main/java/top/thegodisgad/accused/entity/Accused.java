package top.thegodisgad.accused.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 举报
 * @TableName tb_accused
 */
@Data
public class Accused implements Serializable {
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
     * 举报人标识
     */
    private Long userId;

    /**
     * 被告人类型;1:用户;2:商品;3:贴子;4:信息
     */
    private Integer defendantType;

    /**
     * 被告人标识
     */
    private Long defendantId;

    /**
     * 处理人
     */
    private Long handler;

    /**
     * 处理时间
     */
    private Date handlingTime;

    /**
     * 处理信息
     */
    private Long handingMessage;

    /**
     * 举报类型
     */
    private Integer reportType;

    /**
     * 举报信息
     */
    private String reportMessage;

    private static final long serialVersionUID = 1L;
}