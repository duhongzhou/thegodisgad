package top.thegodisgad.school.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学院
 * @TableName tb_academy
 */
@Data
public class Academy implements Serializable {
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
     * 学院标识
     */
    private Long schoolId;

    /**
     * 学院标识
     */
    private Long academyId;

    /**
     * 学院名称
     */
    private String academyName;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;
}