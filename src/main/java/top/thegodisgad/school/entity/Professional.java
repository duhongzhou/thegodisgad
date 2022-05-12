package top.thegodisgad.school.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 专业
 * @TableName tb_professional
 */
@Data
public class Professional implements Serializable {
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
     * 专业标识
     */
    private Long professionalId;

    /**
     * 门类
     */
    private String professionalCategory;

    /**
     * 专业类
     */
    private String professionalClass;

    /**
     * 专业代码
     */
    private String professionalCode;

    /**
     * 专业名称
     */
    private String professionalName;

    /**
     * 学位授予门类
     */
    private String degreeAwardingCategory;

    /**
     * 修业年限
     */
    private Integer schoolingLength;

    private static final long serialVersionUID = 1L;
}